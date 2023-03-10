package com.kakapo.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.window.layout.DisplayFeature
import com.kakapo.ui.ScreenNotImplementedYet
import com.kakapo.ui.utils.CWMContentType
import com.kakapo.ui.utils.CWMNavigationType

@Composable
internal fun SettingsRoute(
    contentType: CWMContentType,
    navigationType: CWMNavigationType,
    navController: NavHostController,
    displayFeature: List<DisplayFeature>,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel()
){
    SettingsScreen(modifier = modifier)
}

@Composable
internal fun SettingsScreen(modifier: Modifier){
    ScreenNotImplementedYet(screenTitle = "Settings")
}