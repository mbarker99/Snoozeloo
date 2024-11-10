package com.embarkapps.snoozeloo.core.domain.navigation

import androidx.navigation.NavOptionsBuilder
import com.embarkapps.snoozeloo.core.presentation.navigation.Destination

sealed interface NavigationAction {
    data class Navigate(
        val destination: Destination,
        val navOptions: NavOptionsBuilder.() -> Unit = {}
    ) : NavigationAction

    data object NavigateUp : NavigationAction
}