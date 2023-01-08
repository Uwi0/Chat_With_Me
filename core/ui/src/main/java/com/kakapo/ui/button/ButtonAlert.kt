package com.kakapo.ui.button

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kakapo.designsystem.component.CWMTextButton
import com.kakapo.designsystem.theme.CWMTheme
import com.kakapo.designsystem.theme.KarlaFontFamily
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
internal fun ButtonAlertHorizontal(modifier: Modifier  = Modifier,onCancelClicked: () -> Unit, onDiscardClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .widthIn(164.dp)
            .heightIn(54.dp).then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CWMTextButton(
            onClick = onCancelClicked,
            text = {
                Text(
                    text = stringResource(id = R.string.cancel),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium.copy(fontFamily = KarlaFontFamily),
                )
            }
        )
        CWMTextButton(
            onClick = onDiscardClicked,
            text = {
                Text(
                    text = stringResource(id = R.string.discard),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.titleMedium.copy(fontFamily = KarlaFontFamily),
                )
            }
        )
    }
}

@Composable
internal fun ButtonAlertVertical(modifier: Modifier = Modifier,onSelectAnother: () -> Unit, onCancelClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .widthIn(170.dp)
            .heightIn(86.dp).then(modifier),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.End
    ) {
        CWMTextButton(
            onClick = onSelectAnother,
            text = {
                Text(
                    text = stringResource(id = R.string.selected_another_chat),
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
    CWMTheme {
        Column {
            ButtonAlertHighestPriority(onHelpClicked = {}, onConfirmClicked = {})
            ButtonAlertHorizontal(onCancelClicked = { /*TODO*/ }, onDiscardClicked = {})
            ButtonAlertVertical(onSelectAnother = {}, onCancelClicked = {})
        }
    }
}