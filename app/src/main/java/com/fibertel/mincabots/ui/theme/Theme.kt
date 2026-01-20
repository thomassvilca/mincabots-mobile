package com.fibertel.mincabots.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = BrandPrimary,
    onPrimary = Color.White,

    background = Color.White,
    onBackground = Color.Black,

    surface = Color.White,
    onSurface = Color.Black,

    outline = BrandBorder,              // para bordes
    onSurfaceVariant = BrandTextGray,   // textos “grises”
    error = BrandErrorText,
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = BrandPrimary,
    onPrimary = Color.White,

    background = Color(0xFF0F1115),
    onBackground = Color.White,

    surface = Color(0xFF0F1115),
    onSurface = Color.White,

    outline = BrandBorder,
    onSurfaceVariant = Color(0xFFB9BDC7),
    error = BrandErrorText,
    onError = Color.White
)

@Composable
fun MINCABOTSMobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // 👈 recomendado para respetar maqueta
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
