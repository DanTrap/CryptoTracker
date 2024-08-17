package com.feature.home.presentation.home.states

import androidx.compose.runtime.Immutable
import com.core.domain.model.Coin
import com.feature.home.model.Currency

@Immutable
data class HomeState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val currency: Currency = Currency.USD,
)

sealed interface HomeEvent {
    data object OnSettingsClick : HomeEvent
    data class OnRefresh(val currency: Currency) : HomeEvent
    data class OnChipClick(val currency: Currency) : HomeEvent
    data class OnCoinClick(val coin: Coin) : HomeEvent
}

sealed interface HomeSideEffect {
    data object NavigateToSettings : HomeSideEffect
    data object RefreshEnd : HomeSideEffect
    data object RefreshError : HomeSideEffect
    data class NavigateToDetails(val id: String) : HomeSideEffect
}
