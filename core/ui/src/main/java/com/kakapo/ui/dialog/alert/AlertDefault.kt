package com.kakapo.ui.dialog.alert

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.kakapo.ui.R

@Composable
fun AlertDefault(){
    Dialog(onDismissRequest = { /*TODO*/ }) {
        AlertCardWrapper(modifier = Modifier.heightIn(160.dp)) {


        }
    }
}