package com.feature.settings.menu

import com.core.domain.repository.AppReviewManager
import com.core.domain.repository.ContactUsManager
import com.core.mvi.BaseViewModel
import com.core.mvi.emitSideEffect
import com.feature.settings.menu.states.SettingsEvent
import com.feature.settings.menu.states.SettingsSideEffect
import com.feature.settings.menu.states.SettingsState

class SettingsViewModel(
    private val reviewManager: AppReviewManager,
    private val contactUsManager: ContactUsManager,
) : BaseViewModel<SettingsState, SettingsSideEffect, SettingsEvent>(
    initialState = SettingsState()
) {

    override fun onEvent(event: SettingsEvent) {
        when (event) {
            SettingsEvent.OnBackArrowClick -> navigateBack()
            SettingsEvent.OnContactUsClick -> contactUs()
            SettingsEvent.OnLanguageClick -> navigateToLanguages()
            SettingsEvent.OnPrivacyPolicyClick -> navigateToPrivacyPolicy()
            SettingsEvent.OnTermsClick -> navigateToTerms()
            SettingsEvent.OnRateUsClick -> rateUs()
        }
    }

    private fun navigateBack() = emitSideEffect(SettingsSideEffect.NavigateBack)

    private fun navigateToLanguages() = emitSideEffect(SettingsSideEffect.NavigateToLanguages)

    private fun navigateToPrivacyPolicy() =
        emitSideEffect(SettingsSideEffect.NavigateToPrivacyPolicy)

    private fun navigateToTerms() = emitSideEffect(SettingsSideEffect.NavigateToTerms)

    private fun contactUs() = contactUsManager.contactUs()

    private fun rateUs() = reviewManager.reviewApplication()
}
