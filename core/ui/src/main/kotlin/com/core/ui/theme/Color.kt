package com.core.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val White = Color(0xFFFFFFFF)
private val Black = Color(0xFF000000)
private val Azure = Color(0xFF2A9D8F)
private val Curry = Color(0xFFEB5757)
private val Grey = Color(0xFF525252)
private val LightGrey = Color(0xFF9B9B9B)
private val Orange = Color(0xFFF7931A)
private val HoneyWax = Color(0xFFFFAD25)
private val MandarinPeel = Color(0xFFFF9F00)

internal val LightColorScheme = lightColorScheme(
    primary = Orange,
    onPrimary = White,
)

@Immutable
data class AppColors(
    val default: Color = Color.Unspecified,
    val white: Color = White,
    val black: Color = Black,
    val azure: Color = Azure,
    val curry: Color = Curry,
    val grey: Color = Grey,
    val lightGrey: Color = LightGrey,
    val honeyWax: Color = HoneyWax,
    val mandarinPeel: Color = MandarinPeel,
)

internal val LocalAppColors = staticCompositionLocalOf { AppColors() }
