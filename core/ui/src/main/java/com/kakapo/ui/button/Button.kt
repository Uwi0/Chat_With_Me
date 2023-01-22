package com.kakapo.ui.button

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kakapo.designsystem.component.CWMButton
import com.kakapo.designsystem.theme.CWMTheme
import com.kakapo.ui.AppPreview
import com.kakapo.ui.R

@Composable
fun ButtonRegular(
    onClick: () -> Unit,
    @StringRes textButton: Int
) {
    CWMButton(
        modifier = Modifier
            .heightIn(27.dp)
            .widthIn(58.dp),
        onClick = onClick,
        text = {
            Text(text = stringResource(textButton))
        }
    )
}

@Composable
fun ButtonMedium(
    onClick: () -> Unit,
    @StringRes textButton: Int
) {
    CWMButton(
        modifier = Modifier
            .widthIn(146.dp)
            .heightIn(39.dp),
        onClick = onClick,
        text = {
            Text(text = stringResource(textButton))
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.FileCopy, contentDescription = null)
        }
    )
}

@Composable
fun ButtonLarge(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    @StringRes textButton: Int,
    leadingIcon: @Composable () -> Unit = {}
){
    CWMButton(
        modifier = modifier
            .widthIn(min = 311.dp, max = 343.dp)
            .heightIn(47.dp),
        onClick = onClick,
        text = {
            Text(text = stringResource(id = textButton))
        },
        leadingIcon = leadingIcon
    )
}



@AppPreview
@Composable
private fun PreviewButtonRegular() {
    CWMTheme {
        ButtonRegular(onClick = { /*TODO*/ }, textButton = R.string.add)
    }
}

@AppPreview
@Composable
private fun PreviewButtonMedium() {
    CWMTheme {
        ButtonMedium(onClick = { /*TODO*/ }, textButton = R.string.copy)
    }
}

@AppPreview
@Composable
private fun PreviewButtonLarge(){
    CWMTheme {
        ButtonLarge(onClick = { /*TODO*/ }, textButton = R.string.clear_cache)
    }
}