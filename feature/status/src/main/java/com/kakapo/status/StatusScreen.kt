package com.kakapo.status

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.window.layout.DisplayFeature
import com.kakapo.status.navigation.StatusViewModel
import com.kakapo.ui.cell.CellStatusItem
import com.kakapo.ui.utils.CWMContentType
import com.kakapo.ui.utils.CWMNavigationType

@Composable
internal fun StatusRoute(
    contentType: CWMContentType,
    navigationType: CWMNavigationType,
    navController: NavHostController,
    displayFeature: List<DisplayFeature>
) {
    StatusScreen()
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun StatusScreen(viewModel: StatusViewModel = hiltViewModel()) {
    val uiState by viewModel.viewState.collectAsStateWithLifecycle()
    LazyColumn(modifier = Modifier.fillMaxWidth(),content = {
        items(uiState.listStatus) {
            CellStatusItem(statusItem = it)
        }
    })
}