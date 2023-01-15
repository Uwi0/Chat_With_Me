package com.kakapo.authentication.component

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.kakapo.authentication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputDefault(
    query: String,
    onQueryChanged: (String) -> Unit,
    @StringRes hint: Int,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        maxLines = 1,
        singleLine = true,
        leadingIcon = {
            Spacer(modifier = Modifier.width(16.dp))
        },
        placeholder = {
            Text(
                text = stringResource(id = hint)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
        ),
        shape = CircleShape,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = MaterialTheme.colorScheme.outline
        ),
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.outline, shape = CircleShape)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputPassword(
    query: String,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isVisible by remember { mutableStateOf(false) }
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        singleLine = true,
        leadingIcon = {
            Spacer(modifier = Modifier.width(16.dp))
        },
        trailingIcon = {
            val icon = if (isVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val description = if (isVisible) R.string.hide_password else R.string.show_password
            IconButton(
                onClick = { isVisible = !isVisible },
                content = {
                    Icon(imageVector = icon, contentDescription = stringResource(id = description))
                }
            )
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.password)
            )
        },
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
        ),
        shape = CircleShape,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = MaterialTheme.colorScheme.outline
        ),
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.outline, shape = CircleShape)
    )
}