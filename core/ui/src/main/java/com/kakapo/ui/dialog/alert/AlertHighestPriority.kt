package com.kakapo.ui.dialog.alert

import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kakapo.designsystem.theme.CWMTheme
import com.kakapo.ui.AppPreview
import com.kakapo.ui.R
import com.kakapo.ui.button.ButtonAlertHighestPriority

@Composable
fun AlertHighestPriority(){
    AlertCardWrapper(modifier = Modifier.heightIn(142.dp)) {
        ContentAlertDialog(title = R.string.number_blocked, body = R.string.number_is_blocked)
        ButtonAlertHighestPriority(onHelpClicked = { /*TODO*/ }) {

        }
    }
}


@AppPreview
@Composable
private fun PreviewAlertHighestPriority(){
    CWMTheme {
        AlertHighestPriority()
    }
}