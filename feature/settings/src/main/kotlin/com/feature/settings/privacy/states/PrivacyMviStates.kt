package com.feature.settings.privacy.states

sealed interface PrivacyEvent {
    data object OnBackArrowClick : PrivacyEvent
}

sealed interface PrivacySideEffect {
    data object NavigateBack : PrivacySideEffect
}
