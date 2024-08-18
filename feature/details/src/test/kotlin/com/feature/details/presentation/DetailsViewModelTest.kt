package com.feature.details.presentation

import com.core.common.network.Resource
import com.core.common.network.ResponseError
import com.core.domain.model.CoinDetails
import com.core.domain.usecase.GetCoinDetailsUseCase
import com.feature.details.presentation.details.DetailsViewModel
import com.feature.details.presentation.details.states.DetailsEvent
import com.feature.details.presentation.details.states.DetailsSideEffect
import com.feature.details.presentation.details.states.DetailsState
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

class DetailsViewModelTest {

    private val id = "bitcoin"
    private lateinit var getCoinDetailsUseCase: GetCoinDetailsUseCase

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getCoinDetailsUseCase = mockk()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun test_fetch_coin_details_success() = runTest {
        val expectedDetails = CoinDetails(id, "btc", "bitcoin", listOf("a", "b"), "desc", "url")
        val expectedState = DetailsState(
            coinDetails = expectedDetails,
            isError = false,
            isLoading = false,
            responseError = null
        )
        every { getCoinDetailsUseCase(id) } returns flowOf(Resource.Success(expectedDetails))

        DetailsViewModel(id, getCoinDetailsUseCase).test(this, DetailsState()) {
            expectInitialState()
            expectState(expectedState)
        }
        verify(exactly = 1) { getCoinDetailsUseCase(id) }
    }

    @Test
    fun test_fetch_coin_details_from_cache_success() = runTest {
        val expectedDetails = CoinDetails(id, "btc", "bitcoin", listOf("a", "b"), "desc", "url")
        val expectedState = DetailsState(
            coinDetails = expectedDetails,
            isError = false,
            isLoading = false,
            responseError = null
        )
        every { getCoinDetailsUseCase(id) } returns flowOf(Resource.FromCache(expectedDetails))

        DetailsViewModel(id, getCoinDetailsUseCase).test(this, DetailsState()) {
            expectInitialState()
            expectState(expectedState)
        }
        verify(exactly = 1) { getCoinDetailsUseCase(id) }
    }

    @Test
    fun test_fetch_coin_details_error() = runTest {
        val expectedError = ResponseError.CLIENT
        val expectedState = DetailsState(
            coinDetails = null,
            isError = true,
            isLoading = false,
            responseError = expectedError
        )
        every { getCoinDetailsUseCase(id) } returns flowOf(Resource.Error(expectedError))

        DetailsViewModel(id, getCoinDetailsUseCase).test(this, DetailsState()) {
            expectInitialState()
            expectState(expectedState)
        }
        verify(exactly = 1) { getCoinDetailsUseCase(id) }
    }

    @Test
    fun test_fetch_coin_details_after_error_success() = runTest {
        val expectedError = ResponseError.CLIENT
        val expectedStateFirst = DetailsState(
            coinDetails = null,
            isError = true,
            isLoading = false,
            responseError = expectedError
        )
        val expectedDetails = CoinDetails(id, "btc", "bitcoin", listOf("a", "b"), "desc", "url")
        val expectedStateSecond = DetailsState(
            coinDetails = expectedDetails,
            isError = false,
            isLoading = false,
            responseError = null
        )
        val firstEmission = flowOf(Resource.Error(expectedError))
        val secondEmission = flowOf(Resource.Success(expectedDetails))
        every { getCoinDetailsUseCase(id) } returns firstEmission andThen secondEmission

        DetailsViewModel(id, getCoinDetailsUseCase).test(this, DetailsState()) {
            expectInitialState()
            expectState(expectedStateFirst)
            containerHost.onEvent(DetailsEvent.OnTryAgainClick)
            expectState(expectedStateSecond)
        }
        verify(exactly = 2) { getCoinDetailsUseCase(id) }
    }

    @Test
    fun test_side_effect_when_back_click() = runTest {
        every { getCoinDetailsUseCase(id) } returns flowOf()
        DetailsViewModel(id, getCoinDetailsUseCase).test(this, DetailsState()) {
            expectInitialState()
            containerHost.onEvent(DetailsEvent.OnBackClick)
            expectSideEffect(DetailsSideEffect.NavigateBack)
        }
    }
}
