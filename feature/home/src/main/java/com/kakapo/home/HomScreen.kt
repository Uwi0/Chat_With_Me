package com.kakapo.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.window.layout.DisplayFeature
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane
import com.kakapo.chat_detail.ChatDetailScreenSidePanel
import com.kakapo.designsystem.component.AnimateFabContent
import com.kakapo.home.component.HomeSearchBar
import com.kakapo.ui.cell.CellAvatarItem
import com.kakapo.ui.utils.CWMContentType
import com.kakapo.ui.utils.CWMNavigationType

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun HomeRoute(
    contentType: CWMContentType,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigationType: CWMNavigationType,
    navController: NavHostController,
    displayFeature: List<DisplayFeature>
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (contentType == CWMContentType.DUAL_PANE) {
        TwoPane(
            first = {
                HomeScreen(uiState = uiState, viewModel = viewModel)
            },
            second = {
                ChatDetailScreenSidePanel(chatId = uiState.selectedChatId)
            },
            strategy = HorizontalTwoPaneStrategy(splitFraction = 0.5f, gapWidth = 8.dp),
            displayFeatures = displayFeature
        )
    } else {
        HomeScreen(uiState = uiState, viewModel = viewModel)
    }
}
@Composable
internal fun HomeScreen(uiState: HomeViewContract.ViewState, viewModel: HomeViewModel) {

    val scrollState = rememberScrollState()
    BoxWithConstraints(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.verticalScroll(scrollState),
            content = {

                HomeSearchBar(
                    query = uiState.searchQuery,
                    onQueryChanged = viewModel::querySearchChat,
                    onSearch = viewModel::searchChat,
                    onClickImageProfile = {}
                )
                uiState.listChat.forEach {
                    CellAvatarItem(chatItem = it, onItemClicked = viewModel::navigateToChatDetail)
                }
            }
        )

        val fabExtended by remember { derivedStateOf { scrollState.value == 0 } }
        StartConversationFab(
            extended = fabExtended,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {

        }
    }
}

@Composable
private fun StartConversationFab(
    extended: Boolean,
    modifier: Modifier = Modifier,
    onFabClicked: () -> Unit = {}
) {
    FloatingActionButton(
        modifier = modifier
            .padding(16.dp)
            .navigationBarsPadding()
            .height(48.dp)
            .widthIn(48.dp),
        onClick = onFabClicked,
        containerColor = MaterialTheme.colorScheme.tertiaryContainer
    ) {
        AnimateFabContent(
            icon = {
                Icon(
                    imageVector = Icons.Default.Message,
                    contentDescription = stringResource(id = R.string.start_chat)
                )
            },
            text = {
                Text(text = stringResource(id = R.string.start_chat))
            },
            extended = extended
        )
    }
}