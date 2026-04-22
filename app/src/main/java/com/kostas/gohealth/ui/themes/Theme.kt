package com.kostas.gohealth.ui.themes

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
    background = Color(0xFFEAE3D9),
    onBackground = Color.Black,
    surface = Color(0xFFEAE3D9),
    onSurface = Color.Black,
    surfaceContainer = Color(0xFFDFD5C5),
    primary = Color(0xFFF4EFE6),
    onPrimary = Color.Black
)

private val DarkColorScheme = darkColorScheme(
    background = Color(0xFF35333A),
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF35333A),
    onSurface = Color(0xFFE6E1E5),
    surfaceContainer = Color(0xFF29272E),
    primary = Color(0xFF4E4C54),
    onPrimary = Color(0xFFE6E1E5),
)


// Chooses the app's theme, either dark, light, dynamic dark or dynamic light
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
