package com.kakapo.ui.button

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kakapo.designsystem.component.CWMTextButton
import com.kakapo.designsystem.theme.ChatWithMeTheme
import com.kakapo.ui.AppPreview
import com.kakapo.ui.R

@Composable
internal fun ButtonAlertHighestPriority(onHelpClicked: () -> Unit, onConfirmClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CWMTextButton(
            onClick = onHelpClicked,
            text = {
                Text(
                    text = stringResource(id = R.string.help),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                )
            }
        )
        CWMTextButton(
            onClick = onConfirmClicked,
            text = {
                Text(
                    text = stringResource(id = R.string.confirm),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                )
            }
        )
    }
}

@Composable
internal fun ButtonAlertHorizontal(onCancelClicked: () -> Unit, onDiscardClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .widthIn(164.dp)
            .heightIn(54.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CWMTextButton(
            onClick = onCancelClicked,
            text = {
                Text(
                    text = stringResource(id = R.string.cancel),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                )
            }
        )
        CWMTextButton(
            onClick = onDiscardClicked,
            text = {
                Text(
                    text = stringResource(id = R.string.discard),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                )
            }
        )
    }
}

@Composable
internal fun ButtonAlertVertical(onSelectAnother: () -> Unit, onCancelClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .widthIn(170.dp)
            .heightIn(86.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CWMTextButton(
            onClick = onSelectAnother,
            text = {
                Text(
                    text = stringResource(id = R.string.selecte_another_chat),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                )
            }
        )
        CWMTextButton(
            onClick = onCancelClicked,
            text = {
                Text(
                    text = stringResource(id = R.string.cancel_forward),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                )
            }
        )
    }
}

@AppPreview
@Composable
private fun PreviewButtonAlertHighestPriority() {
    ChatWithMeTheme {
        Column {
            ButtonAlertHighestPriority(onHelpClicked = {}, onConfirmClicked = {})
            ButtonAlertHorizontal(onCancelClicked = { /*TODO*/ }, onDiscardClicked = {})
            ButtonAlertVertical(onSelectAnother = {}, onCancelClicked = {})
        }
    }
}