package com.rmakiyama.sealion.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.compositeOver

private val DarkColorPalette = darkColors(
    primary = SeaLionBlue200,
    primaryVariant = SeaLionBlue700,
    secondary = SeaLionOrange200,
).also { colors ->
    colors.copy(
        background = colors.primary.copy(alpha = 0.08f).compositeOver(colors.surface),
        surface = colors.primary.copy(alpha = 0.08f).compositeOver(colors.surface)
    )
}

private val LightColorPalette = lightColors(
    primary = SeaLionBlue500,
    primaryVariant = SeaLionBlue700,
    secondary = SeaLionOrange400,
    background = SeaLionBrown50,
    surface = SeaLionBrown50,
)

@Composable
fun SeaLionTheme(
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
