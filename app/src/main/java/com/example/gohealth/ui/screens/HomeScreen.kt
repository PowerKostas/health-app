package com.example.gohealth.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gohealth.R
import com.example.gohealth.ui.components.home.ProgressBox

@Composable
fun HomeScreen() {
    HorizontalDivider(color = MaterialTheme.colorScheme.onSurface)

    Column(
        verticalArrangement = Arrangement.spacedBy(
            alignment = Alignment.CenterVertically,
            space = 144.dp
        ),

        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)

    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(40.dp)) {
            val modifier = Modifier.weight(1f)
            ProgressBox(modifier, R.drawable.water, Color(0xFF2196F3))
            ProgressBox(modifier, R.drawable.calories, Color(0xFF8B4513))
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(40.dp)) {
            val modifier = Modifier.weight(1f)
            ProgressBox(modifier, R.drawable.push_ups, Color.Black)
            ProgressBox(modifier, R.drawable.steps, Color(0xFFE0AC69))
        }
    }
}
