package com.example.gohealth

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gohealth.ui.components.central.DrawerMenu
import com.example.gohealth.ui.themes.GoHealthTheme
import com.example.gohealth.ui.viewModels.CharacteristicsViewModel
import com.example.gohealth.ui.viewModels.SettingsViewModel
import com.example.gohealth.ui.viewModels.TrackingsViewModel

// This is where the program starts, sets basic settings and runs the custom drawer menu function, which is the center of the app
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }

        setContent {
            val settingsViewModel: SettingsViewModel = viewModel(factory = SettingsViewModel.Factory)
            val characteristicsViewModel: CharacteristicsViewModel = viewModel(factory = CharacteristicsViewModel.Factory)
            val trackingsViewModel: TrackingsViewModel = viewModel(factory = TrackingsViewModel.Factory)

            // Settings is the table with the primary key, when it's first initialized here, the other 2 tables with the foreign keys also
            // get initialized
            val settingsList by settingsViewModel.settings.collectAsState()
            LaunchedEffect(settingsList) {
                if (settingsList.isNotEmpty()) {
                    val userId = settingsList.first().userId
                    characteristicsViewModel.initializeUserCharacteristics(userId)
                    trackingsViewModel.initializeUserTrackings(userId)
                }
            }

            // Gets the set theme option and passes it to the function that sets the theme
            val userSettings = settingsList.firstOrNull()
            if (userSettings != null) {
                val isDarkTheme = when (userSettings.appearance) {
                    "Light" -> false
                    "Dark" -> true
                    else -> isSystemInDarkTheme()
                }

                val useDynamicColor = userSettings.appearance == "Dynamic"

                GoHealthTheme(darkTheme = isDarkTheme, dynamicColor = useDynamicColor) {
                    DrawerMenu()
                }
            }
        }
    }
}
