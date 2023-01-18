package com.kakapo.authentication.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.window.layout.DisplayFeature
import com.kakapo.authentication.login.LoginRoute
import com.kakapo.authentication.login.LoginScreen
import com.kakapo.ui.utils.CWMContentType
import com.kakapo.ui.utils.CWMNavigationType

const val LOGIN_ROUTE = "login_route"

fun NavController.navigateToLogin(startDestination: String){
    this.navigate(LOGIN_ROUTE){
        popUpTo(startDestination){
            inclusive = true
            saveState = false
        }
    }
}

fun NavGraphBuilder.loginScreen(
    contentType: CWMContentType,
    navigationType: CWMNavigationType,
    navController: NavHostController,
    displayFeature: List<DisplayFeature>
){
    composable(LOGIN_ROUTE){
        LoginRoute(
            contentType = contentType,
            navigationType = navigationType,
            navController = navController,
            displayFeature = displayFeature
        )
    }
}