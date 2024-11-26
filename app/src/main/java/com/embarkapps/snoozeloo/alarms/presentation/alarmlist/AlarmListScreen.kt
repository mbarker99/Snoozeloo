package com.embarkapps.snoozeloo.alarms.presentation.alarmlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.embarkapps.snoozeloo.alarms.presentation.alarmlist.components.AlarmCard
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.SnoozelooTheme
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.montserratFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmListScreen(
    state: AlarmListState,
    onEvent: (AlarmListUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    val topAppBarColors = TopAppBarDefaults.topAppBarColors().copy(
        containerColor = Color(0xFFF6F6F6)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Your Alarms",
                        fontFamily = montserratFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        lineHeight = 29.26.sp
                    )
                },
                colors = topAppBarColors
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add alarm"
                    )
                },
                onClick = { onEvent(AlarmListUiEvent.OnAddAlarmClicked) },
                shape = CircleShape,
                containerColor = Color(0xFF4664FF),
                contentColor = Color.White
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) { paddingValues ->
        if (state.alarms.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.alarms) { alarm ->
                    AlarmCard(
                        alarm = alarm,
                        isExtended = false,
                        onEvent = onEvent
                    )
                }
            }
        } else {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(
                    text = "No alarms have been created.",
                    fontFamily = montserratFontFamily,
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp,
                    lineHeight = 19.5.sp
                )
            }
        }

    }

}

@Preview
@Composable
fun AlarmListScreenPreview(modifier: Modifier = Modifier) {
    SnoozelooTheme {
        AlarmListScreen(
            state = AlarmListState(
                isLoading = false,
                alarms = emptyList()
            ),
            onEvent = {}
        )
    }

}

/*
alarms = (1..5).map {
    previewAlarm.copy(id = it)
}
*/