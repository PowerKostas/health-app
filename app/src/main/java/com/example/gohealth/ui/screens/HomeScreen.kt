package com.example.gohealth.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gohealth.R
import com.example.gohealth.helpers.calculateCaloriesGoal
import com.example.gohealth.helpers.calculatePushUpsGoal
import com.example.gohealth.helpers.calculateStepsGoal
import com.example.gohealth.helpers.calculateWaterGoal
import com.example.gohealth.ui.components.home.ProgressBox
import com.example.gohealth.ui.viewModels.CharacteristicsViewModel
import com.example.gohealth.ui.viewModels.TrackingsViewModel

@Composable
fun HomeScreen() {
    // Gets values from the database
    val trackingsViewModel = viewModel<TrackingsViewModel>(factory = TrackingsViewModel.Factory)
    val userTrackingsList by trackingsViewModel.trackings.collectAsState()
    val userTrackings = userTrackingsList.firstOrNull()

    val waterProgress = userTrackings?.waterProgress ?: 0f
    val caloriesProgress = userTrackings?.caloriesProgress ?: 0f
    val pushUpsProgress = userTrackings?.pushUpsProgress ?: 0f
    val stepsProgress = userTrackings?.stepsProgress ?: 0f

    val characteristicsViewModel = viewModel<CharacteristicsViewModel>(factory = CharacteristicsViewModel.Factory)
    val userCharacteristicsList by characteristicsViewModel.characteristics.collectAsState()
    val userCharacteristics = userCharacteristicsList.firstOrNull()

    val waterGoal = calculateWaterGoal(userCharacteristics)
    val caloriesGoal = calculateCaloriesGoal(userCharacteristics)
    val pushUpsGoal = calculatePushUpsGoal(userCharacteristics)
    val stepsGoal = calculateStepsGoal(userCharacteristics)

    if (userTrackings != null || userCharacteristics != null) { // Waits for the database to load
        // Draws the screen
        HorizontalDivider(color = MaterialTheme.colorScheme.onSurface)

        Column(
            verticalArrangement = Arrangement.spacedBy(64.dp),
            modifier = Modifier
                .padding(12.dp, 32.dp, 12.dp, 32.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ProgressBox(R.drawable.water, "Water", Color(0xFF2196F3), (waterProgress / waterGoal).coerceAtMost(1.0f))
            ProgressBox(R.drawable.calories, "Calories", Color(0xFF8B4513), (caloriesProgress / caloriesGoal).coerceAtMost(1.0f))
            ProgressBox(R.drawable.push_ups, "Push-ups", Color.Black, (pushUpsProgress / pushUpsGoal).coerceAtMost(1.0f))
            ProgressBox(R.drawable.steps, "Steps", Color(0xFFE0AC69), (stepsProgress / stepsGoal).coerceAtMost(1.0f))
        }
    }
}
