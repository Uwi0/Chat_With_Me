package com.kakapo.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kakapo.designsystem.theme.CWMTheme
import com.kakapo.home.R
import com.kakapo.ui.AppPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeSearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: () -> Unit,
    onClickImageProfile: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
        , shape = CircleShape
    )
    {
        TextField(
            value = query,
            onValueChange = onQueryChanged,
            leadingIcon = {
                Icon(
                    modifier = Modifier.padding(start = 16.dp),
                    imageVector = Icons.Rounded.Search,
                    contentDescription = stringResource(id = R.string.search_chat)
                )
            },
            trailingIcon = {
                Image(
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 16.dp).clip(CircleShape).clickable { onClickImageProfile() },
                    painter = painterResource(id = com.kakapo.ui.R.drawable.img_avatar_sample1),
                    contentDescription = ""
                )
            },
            maxLines = 1,
            placeholder = { Text(text = stringResource(id = R.string.search_chat)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = { onSearch() }),
            shape = CircleShape,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = MaterialTheme.colorScheme.outline
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@AppPreview
@Composable
private fun PreviewHomeSearchBar() {
    CWMTheme {
        HomeSearchBar(
            query = "Test",
            onQueryChanged = {},
            onSearch = {}
        ) { /*TODO*/ }
    }
}
