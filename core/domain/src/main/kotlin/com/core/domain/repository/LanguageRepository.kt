package com.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface LanguageRepository {

    fun language(): Flow<String>

    fun changeLanguage(newLanguage: String)

    fun checkLanguage(savedLocale: String): String
}
