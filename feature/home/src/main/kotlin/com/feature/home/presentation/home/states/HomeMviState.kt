package com.feature.home.presentation.home.states

import androidx.compose.runtime.Immutable

@Immutable
data class HomeState(
    val isLoading: Boolean = false
)

sealed interface HomeEvent {
    data object OnSettingsClick : HomeEvent
}

sealed interface HomeSideEffect {
    data object NavigateToSettings : HomeSideEffect
}
