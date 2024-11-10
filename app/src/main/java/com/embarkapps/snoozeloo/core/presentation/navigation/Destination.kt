package com.embarkapps.snoozeloo.core.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object AlarmsGraph : Destination

    @Serializable
    data object AlarmListScreen : Destination

    @Serializable
    data object AlarmDetailScreen : Destination
}