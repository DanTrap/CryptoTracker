package com.feature.settings.model

import androidx.annotation.StringRes
import com.core.ui.R

// todo
enum class AppLanguage(@StringRes val languageRes: Int, val code: LanguageCode) {
    ENGLISH(R.string.english, LanguageCode.EN),
}

enum class LanguageCode {
    EN
}
