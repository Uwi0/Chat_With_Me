package com.kakapo.status

import com.kakapo.model.StatusItem
import com.kakapo.ui.R

class StatusViewContract {
    data class ViewState(
        val listStatus: List<StatusItem> = StatusViewContract().dummyStatusListItems(),
        val searchQuery: String = ""
    )

    fun dummyStatusListItems() = listOf(
        StatusItem(
            id = 0,
            userAvatar = R.drawable.img_avatar_sample1,
            username = "yumi",
            author = "yumi",
            dateTime = "10.00"
        ),
        StatusItem(
            id = 2,
            userAvatar = R.drawable.img_avatar_sample2,
            username = "dwi",
            author = "dwi",
            dateTime = "12.00"
        ),
    )
}
