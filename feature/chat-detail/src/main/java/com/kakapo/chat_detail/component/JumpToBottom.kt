package com.kakapo.chat_detail.component

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kakapo.chat_detail.R
import com.kakapo.designsystem.theme.CWMTheme
import com.kakapo.ui.AppPreview

private enum class Visibility {
    VISIBLE,
    GONE
}

const val JUMP_TO_BOTTOM_LABEL = "jump to bottom visibility animation"
const val JUMP_TO_BOTTOM_ANIMATION_LABEL = "jump to bottom offset animation"

@Composable
internal fun JumpToBottom(
    enabled: Boolean,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    val transition = updateTransition(
        if (enabled) Visibility.VISIBLE else Visibility.GONE,
        label = JUMP_TO_BOTTOM_LABEL
    )

    val bottomOffset by transition.animateDp(label = JUMP_TO_BOTTOM_ANIMATION_LABEL) {
        if (it == Visibility.GONE) {
            (-32).dp
        } else {
            32.dp
        }
    }

    if (bottomOffset > 0.dp) {
        ExtendedFloatingActionButton(
            text = { Text(text = stringResource(id = R.string.jump_to_bottom)) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.height(18.dp)
                )
            },
            onClick = onClicked,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .offset(x = 0.dp, y = -bottomOffset)
                .height(36.dp)
        )
    }
}

@AppPreview
@Composable
private fun PreviewJumpToBottom() {
    CWMTheme {
        JumpToBottom(enabled = true, onClicked = { })
    }
}