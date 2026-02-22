package com.example.gohealth

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.gohealth.ui.components.DrawerMenu
import com.example.gohealth.ui.themes.GoHealthTheme

// This is where the program starts, does basic settings and runs the custom rawer menu function, which is the center of the app
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }

        setContent {
            GoHealthTheme {
                DrawerMenu()
            }
        }
    }
}
