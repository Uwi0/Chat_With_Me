package com.kakapo.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.window.layout.DisplayFeature
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane
import com.kakapo.chat_detail.ChatDetailScreen
import com.kakapo.home.component.HomeSearchBar
import com.kakapo.ui.cell.CellAvatarItem
import com.kakapo.ui.utils.CWMContentType
import com.kakapo.ui.utils.CWMNavigationType

@Composable
internal fun HomeRoute(
    contentType: CWMContentType,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigationType: CWMNavigationType,
    navController: NavHostController,
    displayFeature: List<DisplayFeature>
) {
    if (contentType == CWMContentType.DUAL_PANE) {
        TwoPane(
            first = {
                HomeScreen(viewModel)
            },
            second = {
                ChatDetailScreen()
            },
            strategy = HorizontalTwoPaneStrategy(splitFraction = 0.5f, gapWidth = 16.dp),
            displayFeatures = displayFeature
        )
    } else {
        HomeScreen(viewModel)
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun HomeScreen(viewModel: HomeViewModel) {
    val uiState by viewModel.viewState.collectAsStateWithLifecycle()
    LazyColumn(
        content = {
            item { 
                HomeSearchBar(
                    query = uiState.searchQuery,
                    onQueryChanged = viewModel::querySearchChat,
                    onSearch = viewModel::searchChat,
                    onClickImageProfile = {}
                )
            }
            items(uiState.listChat){
                CellAvatarItem(chatItem = it)
            }
        }
    )
}