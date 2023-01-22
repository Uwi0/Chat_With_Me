package com.kakapo.ui.dialog.alert

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kakapo.designsystem.component.CWMRadioButton
import com.kakapo.designsystem.theme.CWMTheme
import com.kakapo.ui.AppPreview
import com.kakapo.ui.R
import com.kakapo.ui.button.ButtonAlertHorizontal

@Composable
fun AlertRadioSelector() {
    AlertCardWrapper(
        modifier = Modifier
            .height(332.dp),
    ) {
        AlertTitle()
        AlertContent()
        Spacer(modifier = Modifier.weight(1f))
        ButtonAlertHorizontal(modifier = Modifier.align(Alignment.End),onCancelClicked = { /*TODO*/ }) {
            
        }
    }
}

@Composable
private fun AlertTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(53.dp)
            .padding(top = 20.dp, start = 22.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.title),
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp)
        )
    }
}

@Composable
private fun AlertContent() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        items(4) {
            CWMRadioButton(
                onChecked = { /*TODO*/ },
                isSelected = false,
                radioButtonTitle = {
                    Text(text = stringResource(id = R.string.options))
                }
            )
        }
    }
}


@AppPreview
@Composable
private fun PreviewAlertRadioSelector() {
    CWMTheme {
        AlertRadioSelector()
    }
}