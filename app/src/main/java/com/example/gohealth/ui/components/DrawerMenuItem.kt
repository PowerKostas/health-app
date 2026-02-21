package com.example.gohealth.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

// Drawer menu item with an item image and title
@Composable
fun DrawerMenuItem(title: String, currentScreen: String, onItemClick: (String) -> Unit) {
    NavigationDrawerItem(
        icon = { Icon(imageVector = Icons.Default.Person, contentDescription = title) },
        label = { Text(title) },
        selected = currentScreen == title,
        shape = RectangleShape,
        modifier = Modifier.height(80.dp),
        onClick = { onItemClick(title) }
    )
}
