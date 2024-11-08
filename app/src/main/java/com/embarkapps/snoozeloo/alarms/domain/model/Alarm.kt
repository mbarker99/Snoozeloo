package com.embarkapps.snoozeloo.alarms.domain.model

import java.time.ZonedDateTime

data class Alarm(
    val id: Int,
    val title: String,
    val isEnabled: Boolean,
    val time: ZonedDateTime
)
