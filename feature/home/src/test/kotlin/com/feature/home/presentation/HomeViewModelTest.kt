package com.feature.home.presentation

import com.core.common.network.Resource
import com.core.common.network.ResponseError
import com.core.domain.model.Coin
import com.core.domain.usecase.GetCoinsMarketUseCase
import com.feature.home.model.Currency
import com.feature.home.presentation.home.HomeViewModel
import com.feature.home.presentation.home.states.HomeEvent
import com.feature.home.presentation.home.states.HomeSideEffect
import com.feature.home.presentation.home.states.HomeState
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.orbitmvi.orbit.test.test

class HomeViewModelTest {

    private lateinit var getCoinsMarketUseCase: GetCoinsMarketUseCase

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getCoinsMarketUseCase = mockk()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun test_fetch_coins_market_success() = runTest {
        val expectedList = listOf(
            Coin("1", "bitcoin", "btc", "url", 123.1, -2.3),
            Coin("2", "bitcoin1", "btc1", "url1", 123.2, -2.4),
        )
        val expectedState = HomeState(
            coins = expectedList,
            isError = false,
            isLoading = false,
            responseError = null
        )
        every { getCoinsMarketUseCase("usd") } returns flowOf(Resource.Success(expectedList))

        HomeViewModel(getCoinsMarketUseCase).test(this, HomeState()) {
            expectInitialState()
            expectState(expectedState)
            expectSideEffect(HomeSideEffect.RefreshEnd)
        }
        verify(exactly = 1) { getCoinsMarketUseCase("usd") }
    }

    @Test
    fun test_fetch_coins_market_from_cache_success() = runTest {
        val expectedList = listOf(
            Coin("1", "bitcoin", "btc", "url", 123.1, -2.3),
            Coin("2", "bitcoin1", "btc1", "url1", 123.2, -2.4),
        )
        val expectedState = HomeState(
            coins = expectedList,
            isError = false,
            isLoading = false,
            responseError = null
        )
        every { getCoinsMarketUseCase("usd") } returns flowOf(Resource.FromCache(expectedList))

        HomeViewModel(getCoinsMarketUseCase).test(this, HomeState()) {
            expectInitialState()
            expectState(expectedState)
            expectSideEffect(HomeSideEffect.RefreshEnd)
        }
        verify(exactly = 1) { getCoinsMarketUseCase("usd") }
    }

    @Test
    fun test_fetch_coins_market_error() = runTest {
        val expectedError = ResponseError.CLIENT
        val expectedState = HomeState(
            coins = emptyList(),
            isError = true,
            isLoading = false,
            responseError = expectedError
        )
        every { getCoinsMarketUseCase("usd") } returns flowOf(Resource.Error(expectedError))

        HomeViewModel(getCoinsMarketUseCase).test(this, HomeState()) {
            expectInitialState()
            expectState(expectedState)
            expectSideEffect(HomeSideEffect.RefreshEnd)
        }
        verify(exactly = 1) { getCoinsMarketUseCase("usd") }
    }

    @Test
    fun test_refresh_coins_market_success() = runTest {
        val expectedFirstList = listOf(
            Coin("1", "bitcoin", "btc", "url", 123.1, -2.3),
            Coin("2", "bitcoin1", "btc1", "url1", 123.2, -2.4),
        )
        val expectedFirstState = HomeState(
            coins = expectedFirstList,
            isError = false,
            isLoading = false,
            responseError = null
        )
        val expectedSecondList = listOf(
            Coin("1", "bitcoin", "btc", "url", 123.3, -2.2),
            Coin("2", "bitcoin1", "btc1", "url1", 123.4, -2.3),
        )
        val expectedSecondState = HomeState(
            coins = expectedSecondList,
            isError = false,
            isLoading = false,
            responseError = null
        )
        val firstEmission = flowOf(Resource.Success(expectedFirstList))
        val secondEmission = flowOf(Resource.Success(expectedSecondList))
        every { getCoinsMarketUseCase("usd") } returns firstEmission andThen secondEmission

        HomeViewModel(getCoinsMarketUseCase).test(this, HomeState()) {
            expectInitialState()
            expectState(expectedFirstState)
            expectSideEffect(HomeSideEffect.RefreshEnd)
            containerHost.onEvent(HomeEvent.OnRefresh(Currency.USD))
            expectState(expectedSecondState)
            expectSideEffect(HomeSideEffect.RefreshEnd)
        }
        verify(exactly = 2) { getCoinsMarketUseCase("usd") }
    }

    @Test
    fun test_currency_change() = runTest {
        val expectedFirstList = listOf(
            Coin("1", "bitcoin", "btc", "url", 123.1, -2.3),
            Coin("2", "bitcoin1", "btc1", "url1", 123.2, -2.4),
        )
        val expectedFirstState = HomeState(
            coins = expectedFirstList,
            isError = false,
            isLoading = false,
            responseError = null,
            currency = Currency.USD
        )
        val expectedSecondList = listOf(
            Coin("1", "bitcoin", "btc", "url", 1230.3, -2.2),
            Coin("2", "bitcoin1", "btc1", "url1", 1230.4, -2.3),
        )
        val expectedSecondState = HomeState(
            coins = expectedSecondList,
            isError = false,
            isLoading = false,
            responseError = null,
            currency = Currency.RUB
        )
        val firstEmition = flowOf(Resource.Success(expectedFirstList))
        val secondEmition = flowOf(Resource.Success(expectedSecondList))
        every { getCoinsMarketUseCase("usd") } returns firstEmition
        every { getCoinsMarketUseCase("rub") } returns secondEmition

        HomeViewModel(getCoinsMarketUseCase).test(this, HomeState()) {
            expectInitialState()
            expectState(expectedFirstState)
            expectSideEffect(HomeSideEffect.RefreshEnd)
            containerHost.onEvent(HomeEvent.OnChipClick(Currency.RUB))
            expectState { copy(coins = emptyList(), currency = Currency.RUB) }
            expectState(expectedSecondState)
            expectSideEffect(HomeSideEffect.RefreshEnd)
        }
        verify(exactly = 1) {
            getCoinsMarketUseCase("usd")
            getCoinsMarketUseCase("rub")
        }
    }

    @Test
    fun test_refresh_error() = runTest {
        val expectedList = listOf(
            Coin("1", "bitcoin", "btc", "url", 123.1, -2.3),
            Coin("2", "bitcoin1", "btc1", "url1", 123.2, -2.4),
        )
        val expectedState = HomeState(
            coins = expectedList,
            isError = false,
            isLoading = false,
            responseError = null,
        )
        every { getCoinsMarketUseCase("usd") } returns flowOf(Resource.FromCache(expectedList))

        HomeViewModel(getCoinsMarketUseCase).test(this, HomeState()) {
            expectInitialState()
            expectState(expectedState)
            expectSideEffect(HomeSideEffect.RefreshEnd)
            containerHost.onEvent(HomeEvent.OnRefresh(Currency.USD))
            expectSideEffect(HomeSideEffect.RefreshError)
            expectSideEffect(HomeSideEffect.RefreshEnd)
        }
        verify(exactly = 2) { getCoinsMarketUseCase("usd") }
    }
}
