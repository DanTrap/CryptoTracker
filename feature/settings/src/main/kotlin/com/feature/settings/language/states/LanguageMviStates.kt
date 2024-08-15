package com.feature.settings.language.states

import androidx.compose.runtime.Immutable
import com.feature.settings.model.AppLanguage

@Immutable
data class LanguageState(
    val isLoading: Boolean = true,
    val currentLanguage: AppLanguage = AppLanguage.ENGLISH,
    val appLanguages: List<AppLanguage> = AppLanguage.entries.toList()
)

sealed interface LanguageEvent {
    data object OnBackArrowClick : LanguageEvent
    data class CheckLanguage(val language: String) : LanguageEvent
    data class OnLanguageClick(val language: AppLanguage) : LanguageEvent
}

sealed class LanguageSideEffect {
    data object NavigateBack : LanguageSideEffect()
}
