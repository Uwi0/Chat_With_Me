package com.kakapo.chat_detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.window.layout.DisplayFeature
import com.kakapo.ui.ScreenNotImplementedYet
import com.kakapo.ui.utils.CWMContentType
import com.kakapo.ui.utils.CWMNavigationType

@Composable
internal fun ChatDetailRoute(
    contentType: CWMContentType,
    modifier: Modifier = Modifier,
    navigationType: CWMNavigationType,
    displayFeature: List<DisplayFeature>
){
    ChatDetailScreen(modifier = modifier)
}

@Composable
fun ChatDetailScreen(modifier: Modifier = Modifier, viewModel: ChatDetailViewModel = hiltViewModel()){
    ScreenNotImplementedYet(screenTitle = "Chat Detail")
}