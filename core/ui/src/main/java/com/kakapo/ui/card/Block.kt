package com.kakapo.ui.card

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kakapo.designsystem.theme.ChatWithMeTheme
import com.kakapo.ui.AppPreview
import com.kakapo.ui.R
import com.kakapo.ui.model.Menu

@Composable
fun BlockMenu(
    listSettingMenu: List<Menu>,
    onSettingsClicked: (Int) -> Unit,
    listHelpMenu: List<Menu>,
    onHelpClicked: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .width(360.dp)
            .wrapContentHeight()
    ) {
        item {
            BlockHeader(header = R.string.settings)
        }
        items(listSettingMenu) { menu ->
            BlockMenuComponent(menu = menu, onClicked = onSettingsClicked)
        }
        item {
            BlockHeader(header = R.string.help)
        }
        items(listHelpMenu){ menu ->
            BlockMenuComponent(menu = menu, onClicked = onHelpClicked)
        }
    }
}

@Composable
private fun BlockMenuComponent(menu: Menu, onClicked: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable { onClicked(menu.id) },
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = menu.icon, contentDescription = stringResource(id = menu.name))
            Spacer(modifier = Modifier.width(22.dp))
            Text(
                text = stringResource(id = menu.name),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
private fun BlockHeader(
    @StringRes header: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, top = 19.dp, bottom = 9.dp)
        ) {
            Text(
                text = stringResource(id = header),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W900)
            )
        }
    }
}

@AppPreview
@Composable
private fun PreviewBlockHeader() {
    val settingsMenu = Menu(id = 1, name = R.string.folders, icon = Icons.Default.Folder)
    val helpMenu = Menu(id = 2, name = R.string.ask_a_question, icon = Icons.Outlined.QuestionAnswer)
    val listSettingsMenu = listOf(settingsMenu, settingsMenu, settingsMenu, settingsMenu, settingsMenu)
    val listHelpMenu = listOf(helpMenu, helpMenu, helpMenu)
    ChatWithMeTheme {
        Column {
            BlockMenu(
                listSettingMenu = listSettingsMenu,
                onSettingsClicked = {},
                listHelpMenu = listHelpMenu,
                onHelpClicked = {}
            )
        }
    }
}