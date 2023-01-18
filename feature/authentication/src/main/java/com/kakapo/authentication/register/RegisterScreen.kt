package com.kakapo.authentication.register

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.window.layout.DisplayFeature
import com.kakapo.ui.ScreenNotImplementedYet
import com.kakapo.ui.utils.CWMContentType
import com.kakapo.ui.utils.CWMNavigationType

@Composable
internal fun RegisterRoute(
    contentType: CWMContentType,
    navigationType: CWMNavigationType,
    navController: NavHostController,
    displayFeature: List<DisplayFeature>,
) {
    RegisterScreen()
}

@Composable
internal fun RegisterScreen() {
    ScreenNotImplementedYet(screenTitle = "Register")
}