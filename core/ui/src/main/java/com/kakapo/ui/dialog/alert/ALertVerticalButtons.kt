package com.kakapo.ui.dialog.alert

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kakapo.designsystem.theme.CWMTheme
import com.kakapo.designsystem.theme.KarlaFontFamily
import com.kakapo.ui.AppPreview
import com.kakapo.ui.R
import com.kakapo.ui.button.ButtonAlertVertical

@Composable
fun AlertVerticalButton() {
    AlertCardWrapper(modifier = Modifier.heightIn(192.dp)) {
        ContentAlertDialog(title = R.string.discard_changes, body = R.string.alert_discard)
        ButtonAlertVertical(modifier = Modifier.align(Alignment.End),onSelectAnother = { /*TODO*/ }) {

        }
    }
}

@AppPreview
@Composable
private fun PreviewAlertVerticalButton() {
    CWMTheme {
        AlertVerticalButton()
    }
}