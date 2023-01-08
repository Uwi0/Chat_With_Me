package com.kakapo.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CWMCheckBox(checkBoxTitle: @Composable () -> Unit, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = isChecked, onCheckedChange = onCheckedChange)
        Spacer(modifier = Modifier.width(6.dp))
        checkBoxTitle()
    }
}

@Composable
fun CWMRadioButton(radioButtonTitle: @Composable () -> Unit, isSelected: Boolean, onChecked: () -> Unit){
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = isSelected, onClick = onChecked)
        Spacer(modifier = Modifier.width(6.dp))
        radioButtonTitle()
    }
}