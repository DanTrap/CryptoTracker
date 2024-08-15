package com.feature.settings.terms

import com.core.mvi.BaseViewModel
import com.core.mvi.emitSideEffect
import com.feature.settings.terms.states.TermsEvent
import com.feature.settings.terms.states.TermsSideEffect

class TermsViewModel : BaseViewModel<Unit, TermsSideEffect, TermsEvent>(
    initialState = Unit
) {

    override fun onEvent(event: TermsEvent) {
        when (event) {
            TermsEvent.OnBackArrowClick -> navigateBack()
        }
    }

    private fun navigateBack() = emitSideEffect(TermsSideEffect.NavigateBack)
}
