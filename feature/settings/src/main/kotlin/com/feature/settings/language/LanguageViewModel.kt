package com.feature.settings.language

import androidx.lifecycle.viewModelScope
import com.core.domain.repository.LanguageRepository
import com.core.mvi.BaseViewModel
import com.core.mvi.emitSideEffect
import com.core.mvi.reducer
import com.feature.settings.language.states.LanguageEvent
import com.feature.settings.language.states.LanguageSideEffect
import com.feature.settings.language.states.LanguageState
import com.feature.settings.model.AppLanguage
import com.feature.settings.model.LanguageCode
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LanguageViewModel(
    private val languageRepository: LanguageRepository,
) : BaseViewModel<LanguageState, LanguageSideEffect, LanguageEvent>(
    initialState = LanguageState()
) {

    init {
        observeLocale()
    }

    override fun onEvent(event: LanguageEvent) {
        when (event) {
            LanguageEvent.OnBackArrowClick -> navigateBack()
            is LanguageEvent.OnLanguageClick -> changeLanguage(event.language)
            is LanguageEvent.CheckLanguage -> checkLanguage(event.language)
        }
    }

    private fun checkLanguage(language: String) = languageRepository.checkLanguage(language)

    private fun observeLocale() = languageRepository.language().onEach {
        reducer { state.copy(currentLanguage = it.toAppLanguage(), isLoading = false) }
    }.launchIn(viewModelScope)

    private fun changeLanguage(language: AppLanguage) =
        languageRepository.changeLanguage(language.code.name)

    private fun navigateBack() = emitSideEffect(LanguageSideEffect.NavigateBack)

    private fun String.toAppLanguage(): AppLanguage = runCatching {
        AppLanguage.entries.first {
            it.code == LanguageCode.valueOf(this.uppercase())
        }
    }.getOrDefault(AppLanguage.ENGLISH)
}
