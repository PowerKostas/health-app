package com.example.gohealth.ui.components.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.gohealth.R

// Imported the Coil library to properly load big images (WebP images)
// Adds a profile picture and a button below it that opens a menu to optionally select a new picture
@Composable
fun ProfilePicture(
    profilePictureString: String?,
    onImageSelected: (String) -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    val avatarMap = mapOf(
        "bear" to R.drawable.bear,
        "cat" to R.drawable.cat,
        "dinosaur" to R.drawable.dinosaur,
        "dog" to R.drawable.dog,
        "dolphin" to R.drawable.dolphin,
        "duck" to R.drawable.duck,
        "eagle" to R.drawable.eagle,
        "elephant" to R.drawable.elephant,
        "horse" to R.drawable.horse,
        "lion" to R.drawable.lion,
        "penguin" to R.drawable.penguin,
        "sheep" to R.drawable.sheep
    )

    // Gets the profile picture from the profile picture string, null if it hasn't loaded yet, defaults to the lion if the user hasn't
    // selected anything
    val profilePicture = when (profilePictureString) {
        null -> null
        else -> avatarMap[profilePictureString] ?: R.drawable.dinosaur
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = profilePicture,
            contentDescription = "Animal",
            modifier = Modifier.size(120.dp)
                .border(width = 2.dp, color = MaterialTheme.colorScheme.onPrimary, shape = CircleShape).padding(2.dp)
        )

        Button(
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.onPrimary),
            onClick = {
                focusManager.clearFocus() // Every profile button needs this to clear the focus from the text fields
                showMenu = true }
        ) {
            Text("Change Profile Picture")
        }
    }

    if (showMenu) {
        Dialog(onDismissRequest = { showMenu = false }) {
            Surface(shape = RoundedCornerShape(16.dp)) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) { items(avatarMap.entries.toList()) { (imageString, image) ->
                        AsyncImage(
                            model = image,
                            contentDescription = "Animal",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .border(width = 2.dp, color = MaterialTheme.colorScheme.onPrimary, shape = CircleShape)
                                .padding(2.dp)
                                .clip(CircleShape)
                                .clickable {
                                    onImageSelected(imageString)
                                    showMenu = false
                                }
                        )
                    }
                }
            }
        }
    }
}
