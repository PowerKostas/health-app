package com.example.gohealth.ui.components.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.gohealth.R

// Imported the Coil library to properly load big images (WebP images)
// Adds a profile picture and a button below it that opens a menu to optionally select a new picture
@Composable
fun ProfilePicture() {
    var showMenu by remember { mutableStateOf(false) }

    var currentImage by remember { mutableIntStateOf(R.drawable.lion) }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = currentImage,
            contentDescription = "Animal",
            modifier = Modifier.size(120.dp)
                .border(width = 2.dp, color = Color.Black, shape = CircleShape).padding(2.dp)
        )

        Button(
            border = BorderStroke(width = 1.dp, color = Color.Black),
            onClick = { showMenu = true }
        ) {
            Text("Change Profile Picture")
        }
    }

    if (showMenu) {
        Dialog(onDismissRequest = { showMenu = false }) {
            Surface(shape = RoundedCornerShape(16.dp)) {
                val imageList = listOf(
                    R.drawable.bear, R.drawable.cat, R.drawable.dinosaur, R.drawable.dog,
                    R.drawable.dolphin, R.drawable.duck, R.drawable.eagle, R.drawable.elephant,
                    R.drawable.horse, R.drawable.lion, R.drawable.penguin, R.drawable.sheep
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(imageList) { imageId ->
                        AsyncImage(
                            model = imageId,
                            contentDescription = "Animal",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .border(width = 2.dp, color = Color.Black, shape = CircleShape)
                                .padding(2.dp)
                                .clip(CircleShape)
                                .clickable {
                                    currentImage = imageId
                                    showMenu = false
                                }
                        )
                    }
                }
            }
        }
    }
}
