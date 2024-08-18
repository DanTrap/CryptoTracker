package com.feature.home.presentation.home

import androidx.lifecycle.viewModelScope
import com.core.common.network.Resource
import com.core.domain.model.Coin
import com.core.domain.usecase.GetCoinsMarketUseCase
import com.core.mvi.BaseViewModel
import com.core.mvi.emitSideEffect
import com.core.mvi.reducer
import com.feature.home.model.Currency
import com.feature.home.presentation.home.states.HomeEvent
import com.feature.home.presentation.home.states.HomeSideEffect
import com.feature.home.presentation.home.states.HomeState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    private val getCoinsMarketUseCase: GetCoinsMarketUseCase,
) : BaseViewModel<HomeState, HomeSideEffect, HomeEvent>(
    initialState = HomeState()
) {

    init {
        fetchCoins(Currency.USD)
    }

    override fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.OnSettingsClick -> navigateToSettings()
            is HomeEvent.OnCoinClick -> navigateToCoinDetails(event.coin)
            is HomeEvent.OnRefresh -> fetchCoins(event.currency, true)
            is HomeEvent.OnChipClick -> updateCurrentCurrency(event.currency)
        }
    }

    private fun fetchCoins(currency: Currency, isRefreshing: Boolean = false) =
        getCoinsMarketUseCase(currency.name.lowercase())
            .onEach { resource ->
                when (resource) {
                    is Resource.Error -> reducer {
                        state.copy(isError = true, isLoading = false, responseError = resource.error)
                    }

                    Resource.Loading -> reducer {
                        state.copy(isLoading = true, coins = emptyList(), isError = false)
                    }

                    is Resource.Success -> reducer {
                        state.copy(coins = resource.data, isLoading = false, isError = false)
                    }

                    is Resource.FromCache -> reducer {
                        if (isRefreshing) emitSideEffect(HomeSideEffect.RefreshError)
                        state.copy(coins = resource.data, isLoading = false, isError = false)
                    }
                }
                emitSideEffect(HomeSideEffect.RefreshEnd)
            }.launchIn(viewModelScope)

    private fun updateCurrentCurrency(currency: Currency) = reducer {
        fetchCoins(currency)
        state.copy(coins = emptyList(), currency = currency)
    }

    private fun navigateToSettings() = emitSideEffect(HomeSideEffect.NavigateToSettings)

    private fun navigateToCoinDetails(coin: Coin) =
        emitSideEffect(HomeSideEffect.NavigateToDetails(coin.id))
}
