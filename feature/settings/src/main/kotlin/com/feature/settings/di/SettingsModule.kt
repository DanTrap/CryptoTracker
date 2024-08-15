package com.feature.settings.di

import com.feature.settings.language.LanguageViewModel
import com.feature.settings.menu.SettingsViewModel
import com.feature.settings.privacy.PrivacyViewModel
import com.feature.settings.terms.TermsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureSettingsModule = module {

    viewModelOf(::SettingsViewModel)

    viewModelOf(::LanguageViewModel)

    viewModelOf(::PrivacyViewModel)

    viewModelOf(::TermsViewModel)
}
