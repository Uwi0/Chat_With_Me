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
import com.kakapo.ui.AppPreview
import com.kakapo.ui.R

@Composable
fun CellAvatarItem() {
    Row(
        modifier = Modifier
            .width(360.dp)
            .height(67.dp)
            .padding(start = 12.dp, top = (12.5).dp, bottom = (10.5).dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.img_avatar_sample1),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Text(text = "Uwi", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.W900)
            Text(text = "last seen at 14:40 pm", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@AppPreview
@Composable
private fun PreviewCellAvatarItem() {
    CWMTheme {
        CellAvatarItem()
    }
}