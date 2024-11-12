package com.embarkapps.snoozeloo.alarms.presentation.alarmdetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
    onHourChanged: (String) -> Unit,
    onMinuteChanged: (String) -> Unit,
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
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .width(128.dp),
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.background,

                ) {

                TextField(
                    value = hour.toString(),
                    onValueChange = { onHourChanged(it) },
                    textStyle = TextStyle(
                        color = Color(0xFF858585),
                        fontSize = 52.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = montserratFontFamily,
                        lineHeight = 63.39.sp,
                        textAlign = TextAlign.Center,
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.padding(vertical = 24.dp),
                )
            }

            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                ":",
                style = TextStyle(
                    color = Color(0xFF858585),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = montserratFontFamily,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.padding(4.dp))

            Surface(
                modifier = Modifier
                    .width(128.dp),
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.background
            ) {
                TextField(
                    value = minute.toString(),
                    onValueChange = { onMinuteChanged(it) },
                    textStyle = TextStyle(
                        color = Color(0xFF858585),
                        fontSize = 52.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = montserratFontFamily,
                        lineHeight = 63.39.sp,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.padding(vertical = 24.dp),
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
            minute = 30,
            onHourChanged = {},
            onMinuteChanged = {}
        )
    }
}