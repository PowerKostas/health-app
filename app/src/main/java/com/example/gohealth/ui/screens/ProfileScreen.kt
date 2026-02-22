package com.example.gohealth.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.gohealth.R

// Imported the Coil library to properly load big images (WebP images)
@Composable
fun ProfileScreen() {
    AsyncImage(
        model = R.drawable.lion,
        contentDescription = "Lion",
        modifier = Modifier.size(120.dp).border(width = 2.dp, color = Color.Black, shape = CircleShape).padding(2.dp)
    )
}
