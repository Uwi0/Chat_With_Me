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
import com.kakapo.chatwithme.R

object CWMRoute {
    const val HOME = "HomeRoute"
    const val STATUS = "StatusRoute"
    const val CALLING = "CallingRoute"
    const val SETTINGS = "SettingsRoute"
}

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
        route = CWMRoute.HOME,
        selectedIcon = Icons.Default.Message,
        unselectedIcon = Icons.Default.Message,
        iconTextId = R.string.home
    ),
    CWMTopLevelDestination(
        route = CWMRoute.STATUS,
        selectedIcon = Icons.Default.Article,
        unselectedIcon = Icons.Default.Article,
        iconTextId = R.string.status
    ),
    CWMTopLevelDestination(
        route = CWMRoute.CALLING,
        selectedIcon = Icons.Default.Call,
        unselectedIcon = Icons.Default.Call,
        iconTextId = R.string.call
    ),
    CWMTopLevelDestination(
        route = CWMRoute.SETTINGS,
        selectedIcon = Icons.Default.Settings,
        unselectedIcon = Icons.Default.Settings,
        iconTextId = R.string.settings
    )
)