package com.example.gohealth.ui.components.categories

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddButton(modifier: Modifier, progressBarColour: Color, text: String) {
    Button(
        onClick = { /* TODO: Add action here */ },

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
