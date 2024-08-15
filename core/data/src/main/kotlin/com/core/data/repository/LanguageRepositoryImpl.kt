package com.core.data.repository

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.core.domain.repository.LanguageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class LanguageRepositoryImpl(
    private val context: Context,
    private val dataStore: DataStore<Preferences>,
) : LanguageRepository {

    override fun language(): Flow<String> = dataStore.data.map { preferences ->
        val savedLocale =
            preferences[languageKey] ?: context.resources.configuration.locales[0].language
        if (Build.VERSION.SDK_INT >= 33) {
            checkLanguage(savedLocale)
        } else {
            savedLocale
        }
    }

    override fun checkLanguage(savedLocale: String): String {
        val preferencesLocale = prefLocale()
        return if (preferencesLocale != null && savedLocale != preferencesLocale) {
            CoroutineScope(Dispatchers.IO).launch {
                setLanguage(preferencesLocale)
            }
            preferencesLocale
        } else {
            savedLocale
        }
    }

    override fun changeLanguage(newLanguage: String) {
        CoroutineScope(SupervisorJob()).launch(Dispatchers.Main.immediate) {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(newLanguage))
            withContext(Dispatchers.IO) {
                setLanguage(newLanguage)
            }
        }
    }

    private fun prefLocale(): String? {
        return AppCompatDelegate.getApplicationLocales().get(0)?.language
    }

    private suspend fun setLanguage(language: String) {
        dataStore.edit { savedLanguage ->
            savedLanguage[languageKey] = language
        }
    }

    private companion object {
        val languageKey = stringPreferencesKey("language_key")
    }
}
