package com.kakapo.chat_detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kakapo.ui.FunctionalityNotAvailablePopup
import com.kakapo.chat_detail.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChannelNameBar(
    channelTitle: String,
    channelSubtitle: String,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onIconClicked: () -> Unit
) {
    var functionalityNotAvailablePopupShow by remember { mutableStateOf(false) }
    if (functionalityNotAvailablePopupShow) {
        FunctionalityNotAvailablePopup {
            functionalityNotAvailablePopupShow = false
        }
    }
    CenterAlignedTopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            ChannelIcon {
                onIconClicked()
            }
        },
        title = {
            ChanelTitle(title = channelTitle, subtitle = channelSubtitle)
        },
        actions = {
            ChannelBarAction(
                icon = Icons.Default.Search,
                description = stringResource(id = com.kakapo.ui.R.string.search)
            ) {
                functionalityNotAvailablePopupShow = true
            }
            ChannelBarAction(
                icon = Icons.Default.Info,
                description = stringResource(id = R.string.info)
            ) {
                functionalityNotAvailablePopupShow = true
            }
        }
    )

}

@Composable
private fun ChanelTitle(title: String, subtitle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ChannelBarAction(icon: ImageVector, description: String, onClick: () -> Unit) {
    Icon(
        imageVector = icon,
        contentDescription = description,
        tint = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 16.dp)
            .height(24.dp),
    )
}

@Composable
private fun ChannelIcon(onClick: () -> Unit){
    Image(
        painter = painterResource(id = com.kakapo.ui.R.drawable.img_avatar_sample1),
        contentDescription = stringResource(id = R.string.info_channel),
        modifier = Modifier
            .height(52.dp)
            .clip(CircleShape)
            .clickable { onClick() }
    )
}