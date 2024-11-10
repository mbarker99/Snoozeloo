package com.embarkapps.snoozeloo.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.embarkapps.snoozeloo.alarms.presentation.alarmdetail.AlarmDetailScreen
import com.embarkapps.snoozeloo.alarms.presentation.alarmdetail.components.AlarmDetailViewModel
import com.embarkapps.snoozeloo.alarms.presentation.alarmlist.AlarmListScreen
import com.embarkapps.snoozeloo.alarms.presentation.alarmlist.AlarmListViewModel

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.AlarmListScreen
    ) {
        composable<Destination.AlarmListScreen> {
            val viewModel = hiltViewModel<AlarmListViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            AlarmListScreen(
                state = state,
                onAddAlarmClick = { navController.navigate(Destination.AlarmDetailScreen) }
            )
        }

        composable<Destination.AlarmDetailScreen> {
            val viewModel = hiltViewModel<AlarmDetailViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            AlarmDetailScreen(
                state = state
            )
        }
    }
}