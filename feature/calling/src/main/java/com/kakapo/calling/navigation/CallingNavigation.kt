package com.kakapo.calling.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.window.layout.DisplayFeature
import com.kakapo.calling.CallingRoute
import com.kakapo.ui.utils.CWMContentType
import com.kakapo.ui.utils.CWMNavigationType

const val CALLING_ROUTE = "calling_route"

fun NavController.navigateToCalling(navOptions: NavOptions? = null) {
    this.navigate(CALLING_ROUTE, navOptions)
}

fun NavGraphBuilder.callingScreen(
    contentType: CWMContentType,
    navigationType: CWMNavigationType,
    navController: NavHostController,
    displayFeature: List<DisplayFeature>
) {
    composable(CALLING_ROUTE){
        CallingRoute(
            contentType = contentType,
            navigationType = navigationType,
            navController = navController,
            displayFeature = displayFeature
        )
    }
}
