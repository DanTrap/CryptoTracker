package com.feature.home.presentation.home

import com.core.mvi.BaseViewModel
import com.core.mvi.emitSideEffect
import com.feature.home.presentation.home.states.HomeEvent
import com.feature.home.presentation.home.states.HomeSideEffect
import com.feature.home.presentation.home.states.HomeState

class HomeViewModel : BaseViewModel<HomeState, HomeSideEffect, HomeEvent>(
    initialState = HomeState()
) {

    override fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.OnSettingsClick -> navigateToSettings()
        }
    }

    private fun navigateToSettings() = emitSideEffect(HomeSideEffect.NavigateToSettings)
}
