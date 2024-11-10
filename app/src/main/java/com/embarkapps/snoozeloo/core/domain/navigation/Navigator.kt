package com.embarkapps.snoozeloo.core.domain.navigation

import androidx.navigation.NavOptionsBuilder
import com.embarkapps.snoozeloo.core.presentation.navigation.Destination
import kotlinx.coroutines.flow.Flow

interface Navigator {
    val startDestination: Destination
    val navActions: Flow<NavigationAction>

    suspend fun navigate(destination: Destination, navOptions: NavOptionsBuilder.() -> Unit = {})
    suspend fun navigateUp()
}