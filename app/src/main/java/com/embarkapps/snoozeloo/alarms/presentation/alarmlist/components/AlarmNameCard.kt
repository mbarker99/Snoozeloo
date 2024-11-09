package com.embarkapps.snoozeloo.alarms.presentation.alarmlist.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AlarmNameCard(
    title: String,
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

    }
}