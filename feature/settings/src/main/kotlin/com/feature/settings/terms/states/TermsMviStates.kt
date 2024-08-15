package com.feature.settings.terms.states

sealed interface TermsEvent {
    data object OnBackArrowClick : TermsEvent
}

sealed interface TermsSideEffect {
    data object NavigateBack : TermsSideEffect
}
