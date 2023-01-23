package com.kakapo.model

data class Message(
    val author: String,
    val content: String,
    val timeStamp: String,
    val image: Int? = null,
    val authorImage: Int,
    val isMe: Boolean
)
