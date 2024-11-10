package com.embarkapps.snoozeloo.core.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.embarkapps.snoozeloo.alarms.presentation.alarmdetail.AlarmDetailScreen
import com.embarkapps.snoozeloo.alarms.presentation.alarmdetail.components.AlarmDetailViewModel
import com.embarkapps.snoozeloo.alarms.presentation.alarmlist.AlarmListScreen
import com.embarkapps.snoozeloo.alarms.presentation.alarmlist.AlarmListViewModel
import com.embarkapps.snoozeloo.core.domain.navigation.NavigationAction
import com.embarkapps.snoozeloo.core.domain.navigation.Navigator

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavGraph(navigator: Navigator) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        val navController = rememberNavController()

        ObserveAsEvents(flow = navigator.navActions) { action ->
            when (action) {
                is NavigationAction.Navigate -> navController.navigate(action.destination) {
                    action.navOptions(this)
                }

                NavigationAction.NavigateUp -> navController.navigateUp()
            }
        }

        NavHost(
            navController = navController,
            startDestination = navigator.startDestination
        ) {
            navigation<Destination.AlarmsGraph>(startDestination = Destination.AlarmListScreen) {
                composable<Destination.AlarmListScreen> {
                    val viewModel = hiltViewModel<AlarmListViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    AlarmListScreen(
                        state = state,
                        onEvent = viewModel::eventHandler
                    )
                }

                composable<Destination.AlarmDetailScreen> {
                    val viewModel = hiltViewModel<AlarmDetailViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    AlarmDetailScreen(
                        state = state,
                        onCloseClick = { navController.navigateUp() }
                    )
                }
            }
        }
    }
}