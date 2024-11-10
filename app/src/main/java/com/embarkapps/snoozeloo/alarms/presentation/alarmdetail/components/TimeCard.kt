package com.embarkapps.snoozeloo.alarms.presentation.alarmdetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.SnoozelooTheme
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.montserratFontFamily

@Composable
fun TimeCard(
    hour: Int,
    minute: Int,
    modifier: Modifier = Modifier
) {
    val cardColors = CardDefaults.cardColors().copy(
        containerColor = Color.White,
        contentColor = Color.Black,
        disabledContainerColor = Color.White,
        disabledContentColor = Color.Black
    )

    Card(
        colors = cardColors,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Surface(
                modifier = Modifier
                    .width(128.dp),
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.background,

                ) {
                BasicTextField(
                    state = TextFieldState(
                        initialText = "00"
                    ),
                    textStyle = TextStyle(
                        color = Color(0xFF858585),
                        fontSize = 52.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = montserratFontFamily,
                        lineHeight = 63.39.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(vertical = 24.dp)
                )
            }

            Surface(
                modifier = Modifier
                    .width(128.dp),
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.background
            ) {
                BasicTextField(
                    state = TextFieldState(
                        initialText = "00"
                    ),
                    textStyle = TextStyle(
                        color = Color(0xFF858585),
                        fontSize = 52.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = montserratFontFamily,
                        lineHeight = 63.39.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(vertical = 24.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun TimeCardPreview() {
    SnoozelooTheme {
        TimeCard(
            hour = 3,
            minute = 30
        )
    }
}