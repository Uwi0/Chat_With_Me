package com.kakapo.ui.cell

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kakapo.designsystem.theme.CWMTheme
import com.kakapo.model.StatusItem
import com.kakapo.ui.AppPreview
import com.kakapo.ui.R



@Composable
fun CellStatusItem (
    statusItem: StatusItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 12.dp, top = (12.5).dp, bottom = (10.5).dp, end = 12.dp),
        verticalAlignment =  Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape),
            painter = painterResource(id = statusItem.userAvatar),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                Text(
                    text = statusItem.username,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.W900,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = statusItem.author,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
@AppPreview
@Composable
private fun PreviewCellStatusItem() {
    CWMTheme {
        val statusItem = StatusItem(
            id = 0,
            userAvatar = R.drawable.img_avatar_sample1,
            username = "Uwi",
            author = "Yumi",
            dateTime = "10.00",
        )
        CellStatusItem(statusItem)
    }
}
