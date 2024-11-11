package com.embarkapps.snoozeloo.alarms.presentation.alarmdetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.SnoozelooTheme

@OptIn(ExperimentalMaterial3Api::class)
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

    val timePickerState = rememberTimePickerState(
        initialHour = hour,
        initialMinute = minute,
        is24Hour = false,
    )

    val timePickerColors = TimePickerDefaults.colors().copy(
        containerColor = Color.White,
        selectorColor = Color(0xFF4664FF),
        periodSelectorBorderColor = Color.White,
        periodSelectorSelectedContainerColor = Color(0xFF4664FF),
        periodSelectorSelectedContentColor = Color.White,
        periodSelectorUnselectedContentColor = Color.Black,
        periodSelectorUnselectedContainerColor = MaterialTheme.colorScheme.background,
        timeSelectorSelectedContainerColor = Color(0xFF4664FF),
        timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.background,
        timeSelectorSelectedContentColor = Color.White,
        timeSelectorUnselectedContentColor = Color.Black,
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TimeInput(
            state = timePickerState,
            colors = timePickerColors
        )

    }

    /*        Row(
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
                            textAlign = TextAlign.Center,

                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.padding(vertical = 24.dp)
                    )
                }
            }*/

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