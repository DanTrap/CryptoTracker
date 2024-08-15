package com.feature.settings.privacy

import com.core.mvi.BaseViewModel
import com.core.mvi.emitSideEffect
import com.feature.settings.privacy.states.PrivacyEvent
import com.feature.settings.privacy.states.PrivacySideEffect

class PrivacyViewModel : BaseViewModel<Unit, PrivacySideEffect, PrivacyEvent>(
    initialState = Unit
) {

    override fun onEvent(event: PrivacyEvent) {
        when (event) {
            PrivacyEvent.OnBackArrowClick -> navigateBack()
        }
    }

    private fun navigateBack() = emitSideEffect(PrivacySideEffect.NavigateBack)
}
