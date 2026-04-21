package com.example.gohealth.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import com.example.gohealth.ui.components.categories.AddButton
import com.example.gohealth.ui.components.general.ProgressBar

// Water, calories and push-ups screen
@Composable
fun CategoriesScreen(iconId: Int, progressBarColour: Color, categoryProgress: Int, categoryGoal: Int, metric: String) {
    HorizontalDivider(color = MaterialTheme.colorScheme.onSurface)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "Category",
            tint = Color.Unspecified,
            modifier = Modifier.size(200.dp)
        )

        // For a better view, it rounds up the goal
        val remainder = categoryGoal % 10
        val roundedCategoryGoal = when {
            remainder < 5 -> categoryGoal - remainder
            remainder == 5 -> categoryGoal
            else -> categoryGoal + (10 - remainder)
        }

        Text(
            text = "$categoryProgress / $roundedCategoryGoal $metric",
            style = MaterialTheme.typography.titleLarge
        )

        ProgressBar(20.dp, progressBarColour, categoryProgress.toFloat() / roundedCategoryGoal)

        Text(text = "Add $metric to reach your goal!")

        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                val modifier = Modifier.weight(1f)
                AddButton(modifier, progressBarColour, "1")
                AddButton(modifier, progressBarColour, "10")
                AddButton(modifier, progressBarColour, "100")
            }

            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                val modifier = Modifier.weight(1f)
                AddButton(modifier, progressBarColour, "Custom")
                AddButton(modifier, Color(0xFFE53935), "Delete Previous")
            }
        }
    }
}
