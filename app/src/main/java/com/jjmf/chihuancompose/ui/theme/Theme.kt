package com.jjmf.chihuancompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = ColorP2,
    primaryVariant = ColorP2,
    secondary = ColorP3
)

private val LightColorPalette = lightColors(
    primary = ColorP1,
    primaryVariant = ColorP1,
    secondary = ColorP2
)

@Composable
fun ChihuanComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}