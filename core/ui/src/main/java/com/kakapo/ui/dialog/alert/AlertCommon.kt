package com.kakapo.ui.dialog.alert

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kakapo.designsystem.theme.KarlaFontFamily

@Composable
internal fun AlertCardWrapper(modifier: Modifier = Modifier, cardContent: @Composable ColumnScope.() -> Unit) {
    Card(modifier = Modifier
        .width(298.dp)
        .then(modifier), shape = RoundedCornerShape(7.dp)
    ) {
        cardContent()
    }
}

@Composable
internal fun ContentAlertDialog(@StringRes title: Int, @StringRes body: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 20.dp, bottom = 16.dp, start = 22.dp, end = 22.dp)
    ) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 20.sp,
                fontFamily = KarlaFontFamily
            )
        )
        Spacer(modifier = Modifier.height(11.dp))
        Text(
            text = stringResource(id = body),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}