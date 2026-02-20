package com.example.gohealth.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    background = Color(0xFFE3DBCE),
    onBackground = Color.Black,
    surface = Color(0xFFE3DBCE),
    onSurface = Color.Black,
    primary = Color.White,
    onPrimary = Color.Black,
    secondary = Color.White,
    onSecondary = Color.Black,
    tertiary = Color.Black,
    onTertiary = Color.White,
)

private val DarkColorScheme = darkColorScheme(
    background = Color(0xFF35333A),
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF35333A),
    onSurface = Color(0xFFE6E1E5),
    primary = Color(0xFF413F48),
    onPrimary = Color(0xFFE6E1E5),
    secondary = Color(0xFF413F48),
    onSecondary = Color(0xFFE6E1E5),
    tertiary = Color(0xFFE6E1E5),
    onTertiary = Color(0xFF413F48),
)


@Composable
fun GoHealthTheme(darkTheme: Boolean = isSystemInDarkTheme(), dynamicColor: Boolean = false, content: @Composable () -> Unit) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
