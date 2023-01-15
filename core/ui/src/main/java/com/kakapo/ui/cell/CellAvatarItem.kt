package com.kakapo.ui.cell

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.NotificationsOff
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kakapo.designsystem.theme.CWMTheme
import com.kakapo.model.ChatItem
import com.kakapo.model.ChatStatus
import com.kakapo.ui.AppPreview
import com.kakapo.ui.R

@Composable
fun CellAvatarItem(
    chatItem: ChatItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = (12.5).dp, bottom = (10.5).dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape),
            painter = painterResource(id = chatItem.userAvatar),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                Text(
                    text = chatItem.username,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.W900
                )
                Text(text = chatItem.author, style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = chatItem.lastMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline
                )
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (chatItem.chatStatus != null) {
                        val iconColor =
                            if (chatItem.chatStatus == ChatStatus.ALREADY_READ) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.outline
                        Icon(
                            imageVector = chatItem.chatStatus!!.icon(),
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = iconColor
                        )
                    }
                    Text(
                        text = chatItem.dateTime,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AnimatedVisibility(visible = chatItem.isMuted) {
                        Icon(
                            imageVector = Icons.Default.NotificationsOff,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.outline
                        )
                    }
                    AnimatedVisibility(visible = chatItem.isPinned) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_pinned),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ChatStatus.icon(): ImageVector = when (this) {
    ChatStatus.NOT_SEND -> Icons.Default.Schedule
    ChatStatus.POSTPONED -> Icons.Default.Done
    ChatStatus.ALREADY_READ -> Icons.Default.DoneAll
}

@AppPreview
@Composable
private fun PreviewCellAvatarItem() {
    CWMTheme {
        val chatItem = ChatItem(
            id = 0,
            userAvatar = R.drawable.img_avatar_sample1,
            username = "Uwi",
            author = "Yumi",
            lastMessage = "wkwkwk",
            dateTime = "09:30",
            isPinned = true,
            isMuted = true,
            chatStatus = ChatStatus.NOT_SEND
        )
        CellAvatarItem(chatItem)
    }
}