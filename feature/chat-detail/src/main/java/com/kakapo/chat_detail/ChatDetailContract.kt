package com.kakapo.chat_detail

import com.kakapo.model.Message

data class ChatDetailUiState(
    val loading: Boolean = false,
    val chatSelectedId: Int = 0,
    val message: String = "",
    val channelTitle: String = "Dwi",
    val channelSubtitle: String = "last seen 16:00 pm",
    var channelMessages: List<Message> = emptyList()
) {
    private val _message: MutableList<Message> = channelMessages.toMutableList()

    fun addMessage() {
        val msg = Message(
            author = "me",
            content = message,
            timeStamp = "now",
            image = null,
            authorImage = 0,
            isMe = true
        )
        _message.add(0, msg)
        channelMessages = _message
    }

}