package com.kakapo.model

import java.time.chrono.ChronoLocalDateTime

data class StatusItem(
    val id: Int,
    val userAvatar: Int,
    val username: String,
    val author: String,
    val dateTime: String,
)

