package com.example.gohealth.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gohealth.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerMenu() {
    // drawerState is used to handle the opening and closing of the menu, scope is for the opening and closing animation, whenever the value
    // of currentScreen changes, Compose automatically reruns every line in this code that has currentScreen in it
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var currentScreen by remember { mutableStateOf("Profile") }

    ModalNavigationDrawer(
        drawerState = drawerState,

        drawerContent = {
            ModalDrawerSheet (modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp * 0.7f), drawerContainerColor = MaterialTheme.colorScheme.surface) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color(0xFF55403E))) { append("Go") }
                        withStyle(style = SpanStyle(color = Color(0xFF059669))) { append("Health") }
                    },

                    fontSize = 28.sp,
                    fontFamily = FontFamily(Font(R.font.fredoka_bold)),
                    modifier = Modifier.padding(16.dp)
                )

                HorizontalDivider(color = MaterialTheme.colorScheme.onSurface)

                DrawerMenuItem(
                    title = "Home",
                    currentScreen = currentScreen,
                    onItemClick = { selectedTitle ->
                        currentScreen = selectedTitle
                        scope.launch { drawerState.close() }
                    }
                )

                DrawerMenuItem(
                    title = "Water",
                    currentScreen = currentScreen,
                    onItemClick = { selectedTitle ->
                        currentScreen = selectedTitle
                        scope.launch { drawerState.close() }
                    }
                )

                DrawerMenuItem(
                    title = "Calories",
                    currentScreen = currentScreen,
                    onItemClick = { selectedTitle ->
                        currentScreen = selectedTitle
                        scope.launch { drawerState.close() }
                    }
                )

                DrawerMenuItem(
                    title = "Push-ups",
                    currentScreen = currentScreen,
                    onItemClick = { selectedTitle ->
                        currentScreen = selectedTitle
                        scope.launch { drawerState.close() }
                    }
                )

                DrawerMenuItem(
                    title = "Steps",
                    currentScreen = currentScreen,
                    onItemClick = { selectedTitle ->
                        currentScreen = selectedTitle
                        scope.launch { drawerState.close() }
                    }
                )

                DrawerMenuItem(
                    title = "Profile",
                    currentScreen = currentScreen,
                    onItemClick = { selectedTitle ->
                        currentScreen = selectedTitle
                        scope.launch { drawerState.close() }
                    }
                )

                DrawerMenuItem(
                    title = "Leaderboards",
                    currentScreen = currentScreen,
                    onItemClick = { selectedTitle ->
                        currentScreen = selectedTitle
                        scope.launch { drawerState.close() }
                    }
                )

            }
        }
    ) { Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopBar(
                title = currentScreen,
                onMenuClick = {
                    scope.launch { drawerState.open() }
                }
            )
        }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                when (currentScreen) {
                    "Home" -> Text("This is the Home screen")
                    "Water" -> Text("This is the Water Intake screen")
                    "Calories" -> Text("This is the Calories Intake screen")
                    "Push-ups" -> Text("This is the Push-ups screen")
                    "Steps" -> Text("This is the Steps screen")
                    "Profile" -> Text("This is the Profile screen")
                    "Leaderboards" -> Text("This is the Leaderboards screen")
                }
            }

        }
    }
}
