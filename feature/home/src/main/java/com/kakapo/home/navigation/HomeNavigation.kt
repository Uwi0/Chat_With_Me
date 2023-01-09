package com.kakapo.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.window.layout.DisplayFeature
import com.kakapo.home.HomeRoute
import com.kakapo.ui.utils.CWMContentType
import com.kakapo.ui.utils.CWMNavigationType

const val HOME_ROUTE = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(HOME_ROUTE, navOptions)
}

fun NavGraphBuilder.homeScreen(
    contentType: CWMContentType,
    navigationType: CWMNavigationType,
    displayFeature: List<DisplayFeature>
) {
    composable(route = HOME_ROUTE) {
        HomeRoute(
            contentType = contentType,
            navigationType = navigationType,
            displayFeature = displayFeature
        )
    }
}