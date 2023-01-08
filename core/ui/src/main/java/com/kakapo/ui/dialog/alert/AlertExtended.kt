package com.kakapo.ui.dialog.alert

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kakapo.designsystem.theme.CWMTheme
import com.kakapo.ui.AppPreview
import com.kakapo.ui.R
import com.kakapo.ui.button.ButtonAlertVertical

@Composable
fun AlertExtended() {
    AlertCardWrapper(
        modifier = Modifier
            .heightIn(270.dp),
    ) {
        AlertHeader()
        AlertContent()
        ButtonAlertVertical(
            modifier = Modifier.align(Alignment.End),
            onSelectAnother = { /*TODO*/ }) {

        }
    }
}

@Composable
private fun AlertHeader() {
    Card(
        modifier = Modifier
            .width(298.dp)
            .height(106.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_contact_list),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun AlertContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(68.dp)
            .padding(vertical = 16.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.alert_forward),
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 15.sp)
        )
    }
}

@AppPreview
@Composable
private fun PreviewAllertExtended() {
    CWMTheme {
        AlertExtended()
    }
}