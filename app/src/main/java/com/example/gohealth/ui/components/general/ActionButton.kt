package com.example.gohealth.ui.components.general

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Custom button to do any action
@Composable
fun ActionButton(modifier: Modifier, progressBarColour: Color, text: String, action: () -> Unit) {
    Button(
        onClick = action,

        colors = ButtonDefaults.buttonColors(
            containerColor = progressBarColour,
            contentColor = Color.White
        ),

        shape = RoundedCornerShape(12.dp),
        modifier = modifier,
    ) {
        Text(text = text)
    }
}
