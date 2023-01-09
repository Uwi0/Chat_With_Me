package com.kakapo.model

data class ChatItem(
    val id: Int,
    val userAvatar: Int,
    val username: String,
    val author: String,
    val lastMessage: String,
    val dateTime: String,
    val isPinned: Boolean,
    val isMuted: Boolean,
    val chatStatus: ChatStatus?
)

enum class ChatStatus {
    NOT_SEND,
    POSTPONED,
    ALREADY_READ
}

