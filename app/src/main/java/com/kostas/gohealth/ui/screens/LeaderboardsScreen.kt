package com.kostas.gohealth.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kostas.gohealth.R
import com.kostas.gohealth.ui.components.screen.LeaderboardRow

@Composable
fun LeaderboardsScreen() {
    HorizontalDivider(color = MaterialTheme.colorScheme.onSurface)

    Column(
        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Text(
                text = "Most Goals Completed",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD4AF37)
            )

            LeaderboardRow(R.drawable.bear, "Kostas", R.drawable.water, "Water", "13")

            LeaderboardRow(R.drawable.elephant, "KostasKostasKostas", R.drawable.calories, "Calories", "865059")

            LeaderboardRow(R.drawable.sheep, "KostasKostasKos", R.drawable.push_ups, "Push-ups", "472504")
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Text(
                text = "Total Steps",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD4AF37)
            )

            LeaderboardRow(R.drawable.dolphin, "KostasKostasKos", R.drawable.steps, "Steps", "10000000")
        }
    }
}
