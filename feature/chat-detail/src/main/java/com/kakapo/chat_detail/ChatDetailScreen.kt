package com.kakapo.chat_detail

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.window.layout.DisplayFeature
import com.kakapo.chat_detail.component.ChannelNameBar
import com.kakapo.chat_detail.component.Message
import com.kakapo.ui.custom_text_field.UserInput
import com.kakapo.ui.utils.CWMContentType
import com.kakapo.ui.utils.CWMNavigationType
import kotlinx.coroutines.launch

@Composable
internal fun ChatDetailRoute(
    contentType: CWMContentType,
    modifier: Modifier = Modifier,
    navigationType: CWMNavigationType,
    navController: NavHostController,
    displayFeature: List<DisplayFeature>
) {
    ChatDetailScreen()
}

@Composable
fun ChatDetailScreenSidePanel(chatId: Int) {
    if (chatId == -1) {
        NotSelectedChatScreen()
    }
    AnimatedVisibility(
        visible = chatId != -1,
        enter = expandHorizontally() + fadeIn(),
        exit = shrinkHorizontally() + fadeOut()
    ) {
        ChatDetailScreen(chatId = chatId)
    }
}

@Composable
private fun NotSelectedChatScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = com.kakapo.designsystem.R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(id = R.string.not_selcted_screen_subtitle),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun ChatDetailScreen(
    viewModel: ChatDetailViewModel = hiltViewModel(),
    chatId: Int = 0
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    viewModel.requestSelectedChat(chatId = chatId)

    stringResource(id = R.string.author_me)

    val scrollState = rememberLazyListState()
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)
    val scope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.windowInsetsPadding(
            WindowInsets
                .navigationBars
                .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
            ) {
                Message(
                    message = uiState.channelMessages,
                    navigateToProfile = viewModel::navigateToProfilePage,
                    scrollState = scrollState,
                    modifier = Modifier.weight(1f)
                )
                UserInput(
                    onMessageSent = viewModel::observeMessage,
                    resetScroll = {
                        scope.launch { scrollState.scrollToItem(0) }
                    },
                    modifier = Modifier
                        .navigationBarsPadding()
                        .imePadding()
                )
            }
            ChannelNameBar(
                channelTitle = uiState.channelTitle,
                channelSubtitle = uiState.channelSubtitle,
                scrollBehavior = scrollBehavior
            ) {}
        }
    }
}

