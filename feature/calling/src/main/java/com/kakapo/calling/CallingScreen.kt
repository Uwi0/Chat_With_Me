package com.kakapo.calling

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.window.layout.DisplayFeature
import com.kakapo.ui.ScreenNotImplementedYet
import com.kakapo.ui.utils.CWMContentType
import com.kakapo.ui.utils.CWMNavigationType

@Composable
internal fun CallingRoute(
    contentType: CWMContentType,
    modifier: Modifier = Modifier,
    navigationType: CWMNavigationType,
    navController: NavHostController,
    displayFeature: List<DisplayFeature>,
    viewModel: CallingViewModel = hiltViewModel()
){
    CallingScreen()
}

@Composable
internal fun CallingScreen(){
    ScreenNotImplementedYet(screenTitle = "Calling")
}