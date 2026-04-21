package com.example.gohealth.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gohealth.R
import com.example.gohealth.ui.components.general.ProgressBar

@Composable
fun StepsScreen(categoryProgress: Int, categoryGoal: Int) {
    HorizontalDivider(color = MaterialTheme.colorScheme.onSurface)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(70.dp, Alignment.CenterVertically),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Currently tracking steps...",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFFE0AC69),
            fontWeight = FontWeight.Bold
        )

        Icon(
            painter = painterResource(id = R.drawable.steps),
            contentDescription = "Category",
            tint = Color.Unspecified,
            modifier = Modifier.size(200.dp)
        )

        val remainder = categoryGoal % 10
        val roundedCategoryGoal = when {
            remainder < 5 -> categoryGoal - remainder
            remainder == 5 -> categoryGoal
            else -> categoryGoal + (10 - remainder)
        }

        Text(
            text = "$categoryProgress / $roundedCategoryGoal steps",
            style = MaterialTheme.typography.titleLarge
        )

        ProgressBar(20.dp, Color(0xFFE0AC69), categoryProgress.toFloat() / roundedCategoryGoal)
    }
}
