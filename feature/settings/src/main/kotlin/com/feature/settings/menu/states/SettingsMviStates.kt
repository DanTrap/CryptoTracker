package com.feature.settings.menu.states

import androidx.compose.runtime.Immutable

@Immutable
data class SettingsState(
    val isLoading: Boolean = false,
)

sealed interface SettingsEvent {
    data object OnBackArrowClick : SettingsEvent
    data object OnRateUsClick : SettingsEvent
    data object OnPrivacyPolicyClick : SettingsEvent
    data object OnTermsClick : SettingsEvent
    data object OnContactUsClick : SettingsEvent
    data object OnLanguageClick : SettingsEvent
}

sealed interface SettingsSideEffect {
    data object NavigateBack : SettingsSideEffect
    data object NavigateToPrivacyPolicy : SettingsSideEffect
    data object NavigateToLanguages : SettingsSideEffect
    data object NavigateToTerms : SettingsSideEffect
}
