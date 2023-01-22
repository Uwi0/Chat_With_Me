package com.kakapo.chat_detail.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.window.layout.DisplayFeature
import com.kakapo.chat_detail.ChatDetailRoute
import com.kakapo.ui.utils.CWMContentType
import com.kakapo.ui.utils.CWMNavigationType

const val CHAT_DETAIL_ROUTE = "chat_detail_route"

fun NavController.navigateToChatDetail(navOptions: NavOptions? = null){
    this.navigate(CHAT_DETAIL_ROUTE, navOptions)
}

fun NavGraphBuilder.chatDetailScreen(
    contentType: CWMContentType,
    navigationType: CWMNavigationType,
    navController: NavHostController,
    displayFeature: List<DisplayFeature>
){
    composable(route = CHAT_DETAIL_ROUTE){
        ChatDetailRoute(
            contentType = contentType,
            navigationType = navigationType,
            navController = navController,
            displayFeature = displayFeature
        )
    }
}