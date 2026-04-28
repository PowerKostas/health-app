package com.kostas.gohealth.ui.components.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LeaderboardRow(profilePictureId: Int, username: String, categoryId: Int, categoryName: String, score: String) {
    CustomSurface {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = profilePictureId),
                contentDescription = "Profile Picture",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(72.dp)
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.onPrimary, shape = CircleShape)
            )

            Column {
                Text(
                    text = username,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = categoryId),
                        contentDescription = "Category",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(24.dp)
                            .offset(x = (-4).dp)
                    )

                    Text(
                        text = categoryName,
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }

            // Adjusts the text's size, if it doesn't fit the screen
            BasicText(
                text = score,
                modifier = Modifier.weight(1f), // Claims the remaining space

                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD4AF37),
                    textAlign = TextAlign.End
                ),

                maxLines = 1,
                autoSize = TextAutoSize.StepBased(minFontSize = 1.sp, maxFontSize = 28.sp)
            )
        }
    }
}
