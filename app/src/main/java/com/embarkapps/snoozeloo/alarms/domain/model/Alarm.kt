package com.embarkapps.snoozeloo.alarms.domain.model

import java.time.LocalDateTime

data class Alarm(
    val title: String,
    val time: LocalDateTime,
    val isEnabled: Boolean,
    val id: Long
)
