package com.kakapo.ui.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class Menu(
    val id: Int,
    @StringRes val name: Int,
    val icon: ImageVector
)


object MenuId{
    const val NotificationAndSound = 1
    const val DataAndStorage = 2
    const val ChatSettings = 3
    const val Folders = 4
    const val Devices = 5
    const val Language = 6
    const val AskAQuestion = 7
    const val ChatWithMeFaq = 8
    const val PrivacyPolice = 9
}