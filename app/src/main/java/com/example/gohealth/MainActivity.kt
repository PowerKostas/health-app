package com.example.gohealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.gohealth.ui.theme.GoHealthTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            GoHealthTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                    topBar = {
                        TopAppBar(
                            title = { Text("Profile") },

                            navigationIcon = {
                                IconButton(onClick = { }) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Menu"
                                    )
                                }
                            },

                            actions = {
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(style = SpanStyle(color = Color(0xFF55403E))) { append("Go") }
                                        withStyle(style = SpanStyle(color = Color(0xFF059669))) { append("Health") }
                                    },

                                    fontSize = 28.sp,

                                    fontFamily = FontFamily(Font(R.font.fredoka_bold))
                                )
                            }
                        )
                    }
                ) { innerPadding ->
                    ProfileScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun ProfileScreen(modifier : Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Hello World!")
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen()
    }
}
