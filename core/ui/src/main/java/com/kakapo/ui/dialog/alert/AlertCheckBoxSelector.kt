package com.kakapo.ui.dialog.alert

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kakapo.designsystem.component.CWMCheckBox
import com.kakapo.designsystem.theme.CWMTheme
import com.kakapo.ui.AppPreview
import com.kakapo.ui.R
import com.kakapo.ui.button.ButtonAlertHighestPriority

@Composable
fun AlertCheckBoxSelector() {
    AlertCardWrapper(modifier = Modifier.heightIn(250.dp)) {
        ContentAlertDialog(title = R.string.cancel_forward_title, body = R.string.alert_discard)
        ListCheckBox()
        ButtonAlertHighestPriority(onHelpClicked = { /*TODO*/ }) {
            
        }
    }
}

@Composable
private fun ListCheckBox() {
    LazyColumn() {
        items(2) {
            CWMCheckBox(
                checkBoxTitle = {
                    Text(text = stringResource(id = R.string.shiping_info))
                },
                isChecked = false,
                onCheckedChange = {}
            )
        }
    }
}

@AppPreview
@Composable
private fun PreviewAlertCheckBoxSelector() {
    CWMTheme {
        AlertCheckBoxSelector()
    }
}