package com.kakapo.chat_detail

data class ChatDetailUiState(
    val loading: Boolean = false,
    val chatSelectedId: Int = 0,
    val message: String = "",
    val channelTitle: String = "Dwi",
    val channelSubtitle: String = "last seen 16:00 pm"
)