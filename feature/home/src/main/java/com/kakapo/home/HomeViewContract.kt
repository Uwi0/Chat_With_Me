package com.kakapo.home

import com.kakapo.model.ChatItem
import com.kakapo.model.ChatStatus
import com.kakapo.ui.R

class HomeViewContract {

    data class ViewState(
        val listChat: List<ChatItem> = HomeViewContract().dummyListChat(),
        val searchQuery: String = ""
    )

    sealed class ViewEvent {
        data class QueryChat(val query: String): ViewEvent()
        object SearchChat: ViewEvent()
    }

    fun dummyListChat() = listOf(
        ChatItem(
            id = 1,
            userAvatar = R.drawable.img_avatar_sample1,
            username = "yumi",
            author = "yumi",
            lastMessage = "wkwkw",
            dateTime = "09:20",
            isPinned = true,
            isMuted = false,
            chatStatus = null
        ),
        ChatItem(
            id = 2,
            userAvatar = R.drawable.img_avatar_sample2,
            username = "aldo",
            author = "you",
            lastMessage = "anjai",
            dateTime = "14:20",
            isPinned = false,
            isMuted = false,
            chatStatus = ChatStatus.POSTPONED
        ),
        ChatItem(
            id = 1,
            userAvatar = R.drawable.img_avatar_sample3,
            username = "arul",
            author = "arul",
            lastMessage = "ok mene yo",
            dateTime = "08:21",
            isPinned = false,
            isMuted = false,
            chatStatus = null
        ),
        ChatItem(
            id = 1,
            userAvatar = R.drawable.img_avatar_sample4,
            username = "jajak",
            author = "you",
            lastMessage = "nyeh -_-",
            dateTime = "20:13",
            isPinned = false,
            isMuted = false,
            chatStatus = ChatStatus.ALREADY_READ
        ),
    )
}

