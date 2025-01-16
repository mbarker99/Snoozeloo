package com.embarkapps.snoozeloo.alarms.presentation.alarmdetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.GrayDisabled
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.SnoozelooTheme
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.montserratFontFamily

@Composable
fun NameInputDialog(
    title: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val nameInputDialogColors = CardDefaults.cardColors().copy(
        containerColor = Color.White,
    )

    val saveButtonColors = ButtonDefaults.buttonColors().copy(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
        disabledContainerColor = GrayDisabled,
        disabledContentColor = Color.White
    )

    val nameInputColors = OutlinedTextFieldDefaults.colors().copy(

    )

    val nameInputTextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = montserratFontFamily,
        lineHeight = 17.07.sp,
    )
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = nameInputDialogColors,
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Alarm Name",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 19.5.sp
                )

                OutlinedTextField(
                    value = title,
                    onValueChange = {},
                    textStyle = nameInputTextStyle
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Button(
                        onClick = {
                            // TODO : save alarm name
                        },
                        colors = saveButtonColors,
                        content = {
                            Text(
                                text = "Save",
                                fontFamily = montserratFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                lineHeight = 19.5.sp,
                                textAlign = TextAlign.Center,
                            )
                        },
                        modifier = Modifier.height(32.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun NameInputDialogPreview() {
    SnoozelooTheme {
        NameInputDialog(
            title = "Work",
            onDismissRequest = { }
        )
    }
}