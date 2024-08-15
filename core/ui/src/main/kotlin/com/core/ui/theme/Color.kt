package com.core.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val White = Color(0xFFFFFFFF)

internal val DarkColorScheme = darkColorScheme(
    onPrimary = White,
)

@Immutable
data class AppColors(
    val default: Color = Color.Unspecified,
    val white: Color = White,
)

internal val LocalAppColors = staticCompositionLocalOf { AppColors() }
