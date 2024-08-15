package com.feature.settings.menu.ui

import androidx.annotation.StringRes
import com.core.ui.R
import com.feature.settings.menu.states.SettingsEvent

enum class SettingsDestinations(
    @StringRes val stringResource: Int,
    val event: SettingsEvent,
) {
    PrivacyPolicy(
        stringResource = R.string.privacy_policy,
        event = SettingsEvent.OnPrivacyPolicyClick
    ),
    TermsOfUse(
        stringResource = R.string.terms_of_use,
        event = SettingsEvent.OnTermsClick
    ),
    Language(
        stringResource = R.string.language,
        event = SettingsEvent.OnLanguageClick
    ),
    WriteUs(
        stringResource = R.string.contact_us,
        event = SettingsEvent.OnContactUsClick
    ),
    RateUs(
        stringResource = R.string.rate_us,
        event = SettingsEvent.OnRateUsClick
    ),
}
