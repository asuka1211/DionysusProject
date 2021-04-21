package com.serma.dionysus.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val LightThemeColors = lightColors(
    primary = PrimaryColor,
    onPrimary = Color.White,
    secondary = Color.White,
    onSecondary = Color.Black,
    background = BackgroundColor,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = Red800,
    onError = Color.White
)

val DarkThemeColors = darkColors(
    primary = PrimaryColor,
    onPrimary = Color.Black,
    secondary = Color.Black,
    onSecondary = Color.White,
    background = BackgroundColor,
    onBackground = Color.White,
    surface = Color.Black,
    onSurface = Color.White,
    error = Red300,
    onError = Color.Black
)

@Composable
fun DionysusTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkThemeColors
    } else {
        LightThemeColors
    }
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
