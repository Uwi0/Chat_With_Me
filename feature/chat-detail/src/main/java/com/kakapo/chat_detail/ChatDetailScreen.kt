package com.kakapo.chat_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.window.layout.DisplayFeature
import com.kakapo.ui.custom_text_field.UserInput
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
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1f))
        UserInput(onMessageSent = {})
    }
}