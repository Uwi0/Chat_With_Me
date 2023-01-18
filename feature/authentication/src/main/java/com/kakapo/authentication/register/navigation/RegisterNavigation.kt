package com.kakapo.authentication.register.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.window.layout.DisplayFeature
import com.kakapo.authentication.register.RegisterRoute
import com.kakapo.ui.utils.CWMContentType
import com.kakapo.ui.utils.CWMNavigationType

const val REGISTER_ROUTE = "register_route"

fun NavController.navigateToRegisterScreen(navOptions: NavOptions? = null) {
    this.navigate(REGISTER_ROUTE, navOptions)
}

fun NavGraphBuilder.registerScreen(
    contentType: CWMContentType,
    navigationType: CWMNavigationType,
    navController: NavHostController,
    displayFeature: List<DisplayFeature>,
) {
    composable(REGISTER_ROUTE) {
        RegisterRoute(
            contentType = contentType,
            navigationType = navigationType,
            navController = navController,
            displayFeature = displayFeature
        )
    }
}