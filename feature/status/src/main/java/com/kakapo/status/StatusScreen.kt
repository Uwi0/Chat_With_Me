package com.kakapo.status

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.window.layout.DisplayFeature
import com.kakapo.ui.ScreenNotImplementedYet
import com.kakapo.ui.utils.CWMContentType
import com.kakapo.ui.utils.CWMNavigationType

@Composable
internal fun StatusRoute(
    contentType: CWMContentType,
    navigationType: CWMNavigationType,
    navController: NavHostController,
    displayFeature: List<DisplayFeature>
){
    StatusScreen()
}

@Composable
internal fun StatusScreen(){
    ScreenNotImplementedYet(screenTitle = "Status")
}