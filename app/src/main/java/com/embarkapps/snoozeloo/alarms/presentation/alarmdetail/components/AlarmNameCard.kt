package com.embarkapps.snoozeloo.alarms.presentation.alarmdetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.embarkapps.snoozeloo.R
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.SnoozelooTheme
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.montserratFontFamily

@Composable
fun AlarmNameCard(
    title: String,
    onTitleChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val textStyle = TextStyle.Default.copy(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 17.07.sp,
        color = Color(0xFF858585),
        textAlign = TextAlign.End,
    )

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.alarm_name),
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 19.5.sp,
        )

        BasicTextField(
            value = title,
            onValueChange = { onTitleChanged(it) },
            textStyle = textStyle,
            singleLine = true,
            decorationBox = { innerTextField ->

                innerTextField()
            }
        )
    }
}


@Preview
@Composable
fun AlarmNameCardPreview(modifier: Modifier = Modifier) {
    SnoozelooTheme {
        AlarmNameCard(
            title = "Wake up",
            onTitleChanged = { }
        )
    }
}