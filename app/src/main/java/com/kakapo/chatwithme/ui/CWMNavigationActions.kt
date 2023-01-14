package com.kakapo.chatwithme.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.kakapo.calling.navigation.CALLING_ROUTE
import com.kakapo.chatwithme.R
import com.kakapo.home.navigation.HOME_ROUTE
import com.kakapo.settings.navigation.SETTINGS_ROUTE
import com.kakapo.status.navigation.STATUS_ROUTE


data class CWMTopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int
)

class CWMNavigationActions(private val navController: NavController) {

    fun navigateTo(destination: CWMTopLevelDestination){
        navController.navigate(destination.route){
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

val TOP_LEVEL_DESTINATIONS = listOf(
    CWMTopLevelDestination(
        route = HOME_ROUTE,
        selectedIcon = Icons.Default.Message,
        unselectedIcon = Icons.Default.Message,
        iconTextId = R.string.home
    ),
    CWMTopLevelDestination(
        route = STATUS_ROUTE,
        selectedIcon = Icons.Default.Article,
        unselectedIcon = Icons.Default.Article,
        iconTextId = R.string.status
    ),
    CWMTopLevelDestination(
        route = CALLING_ROUTE,
        selectedIcon = Icons.Default.Call,
        unselectedIcon = Icons.Default.Call,
        iconTextId = R.string.call
    ),
    CWMTopLevelDestination(
        route = SETTINGS_ROUTE,
        selectedIcon = Icons.Default.Settings,
        unselectedIcon = Icons.Default.Settings,
        iconTextId = R.string.settings
    )
)