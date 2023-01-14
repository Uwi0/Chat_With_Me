package com.kakapo.status.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.window.layout.DisplayFeature
import com.kakapo.status.StatusRoute
import com.kakapo.ui.utils.CWMContentType
import com.kakapo.ui.utils.CWMNavigationType

const val STATUS_ROUTE = "status_route"

fun NavController.navigateToStatusScreen(navOptions: NavOptions? = null){
    this.navigate(STATUS_ROUTE, navOptions)
}

fun NavGraphBuilder.statusScreen(
    contentType: CWMContentType,
    navigationType: CWMNavigationType,
    navController: NavHostController,
    displayFeature: List<DisplayFeature>
){
    composable(STATUS_ROUTE){
        StatusRoute(
            contentType = contentType,
            navigationType = navigationType,
            navController = navController,
            displayFeature = displayFeature
        )
    }
}