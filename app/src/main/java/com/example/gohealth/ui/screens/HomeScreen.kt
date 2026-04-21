package com.example.gohealth.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gohealth.R
import com.example.gohealth.helpers.calculateCaloriesGoal
import com.example.gohealth.helpers.calculatePushUpsGoal
import com.example.gohealth.helpers.calculateStepsGoal
import com.example.gohealth.helpers.calculateWaterGoal
import com.example.gohealth.ui.components.screen.ProgressBox
import com.example.gohealth.ui.viewModels.CharacteristicsViewModel
import com.example.gohealth.ui.viewModels.TrackingsViewModel

@Composable
fun HomeScreen() {
    // Gets values from the database
    val trackingsViewModel = viewModel<TrackingsViewModel>(factory = TrackingsViewModel.Factory)
    val userTrackingsList by trackingsViewModel.trackings.collectAsState()
    val userTrackings = userTrackingsList.firstOrNull()

    val waterProgressSum = userTrackings?.waterProgress?.filterNotNull()?.sum() ?: 0
    val caloriesProgressSum = userTrackings?.caloriesProgress?.filterNotNull()?.sum() ?: 0
    val pushUpsProgressSum = userTrackings?.pushUpsProgress?.filterNotNull()?.sum() ?: 0
    val stepsProgressSum = userTrackings?.stepsProgress?.filterNotNull()?.sum() ?: 0

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
            verticalArrangement = Arrangement.spacedBy(48.dp),
            modifier = Modifier
                .padding(16.dp, 32.dp, 16.dp, 32.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Warning text, if the user hasn't filled all the characteristics in his profile
            if (userCharacteristics == null || userCharacteristics.gender == "" || userCharacteristics.age == null || userCharacteristics.height == null || userCharacteristics.weight == null || userCharacteristics.activityLevel == "" || userCharacteristics.weightGoal == "") {
                Text(
                    text = "Complete your profile for more personalized results.",
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center
                )
            }

            ProgressBox(R.drawable.water, "Water", Color(0xFF2196F3), (waterProgressSum.toFloat() / waterGoal).coerceAtMost(1.0f))
            ProgressBox(R.drawable.calories, "Calories", Color(0xFF8B4513), (caloriesProgressSum.toFloat() / caloriesGoal).coerceAtMost(1.0f))
            ProgressBox(R.drawable.push_ups, "Push-ups", Color.Black, (pushUpsProgressSum.toFloat() / pushUpsGoal).coerceAtMost(1.0f))
            ProgressBox(R.drawable.steps, "Steps", Color(0xFFE0AC69), (stepsProgressSum.toFloat() / stepsGoal).coerceAtMost(1.0f))
        }
    }
}
