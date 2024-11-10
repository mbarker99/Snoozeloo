package com.embarkapps.snoozeloo.core.data.navigation

import androidx.navigation.NavOptionsBuilder
import com.embarkapps.snoozeloo.core.domain.navigation.NavigationAction
import com.embarkapps.snoozeloo.core.domain.navigation.Navigator
import com.embarkapps.snoozeloo.core.presentation.navigation.Destination
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class NavigatorImpl(override val startDestination: Destination) : Navigator {
    private val _navigationActions = Channel<NavigationAction>()
    override val navActions = _navigationActions.receiveAsFlow()

    override suspend fun navigate(
        destination: Destination,
        navOptions: NavOptionsBuilder.() -> Unit
    ) {
        _navigationActions.send(NavigationAction.Navigate(destination, navOptions))
    }

    override suspend fun navigateUp() {
        _navigationActions.send((NavigationAction.NavigateUp))
    }
}