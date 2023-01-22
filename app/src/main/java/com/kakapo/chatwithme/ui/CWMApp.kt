package com.kakapo.chatwithme.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.DisplayFeature
import androidx.window.layout.FoldingFeature
import com.kakapo.authentication.login.navigation.LOGIN_ROUTE
import com.kakapo.authentication.login.navigation.loginScreen
import com.kakapo.authentication.register.navigation.registerScreen
import com.kakapo.calling.navigation.CALLING_ROUTE
import com.kakapo.calling.navigation.callingScreen
import com.kakapo.chat_detail.navigation.chatDetailScreen
import com.kakapo.chatwithme.ui.*
import com.kakapo.home.navigation.HOME_ROUTE
import com.kakapo.home.navigation.homeScreen
import com.kakapo.settings.navigation.SETTINGS_ROUTE
import com.kakapo.settings.navigation.settingsScreen
import com.kakapo.status.navigation.STATUS_ROUTE
import com.kakapo.status.navigation.statusScreen
import com.kakapo.ui.utils.*
import kotlinx.coroutines.launch

private const val START_DESTINATION = HOME_ROUTE

@Composable
fun CWMApp(
    windowSize: WindowSizeClass,
    displayFeature: List<DisplayFeature>
) {

    val foldingDevicePosture = foldingDevicePostureScreenState(displayFeature = displayFeature)
    val (navigationType, contentType) = measureWindowsScreenWidth(windowSize, foldingDevicePosture)
    val navigationContentPosition = measureWindowsScreenHeight(windowSize)
    CWMNavigationWrapper(
        navigationType = navigationType,
        contentType = contentType,
        displayFeature = displayFeature,
        navigationContentPosition = navigationContentPosition
    )
}

private fun foldingDevicePostureScreenState(displayFeature: List<DisplayFeature>): DevicePosture {
    val foldingFeature = displayFeature.filterIsInstance<FoldingFeature>().firstOrNull()
    return when {
        isBookPosture(foldingFeature) -> {
            DevicePosture.BookPosture(foldingFeature.bounds)
        }
        isSeparating(foldingFeature) -> {
            DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)
        }

        else -> DevicePosture.NormalPosture
    }
}

private fun measureWindowsScreenWidth(
    windowSize: WindowSizeClass,
    foldingDevicePosture: DevicePosture
): Pair<CWMNavigationType, CWMContentType> = when (windowSize.widthSizeClass) {
    WindowWidthSizeClass.Compact -> {
        CWMNavigationType.BOTTOM_NAVIGATION to
            CWMContentType.SINGLE_PANE
    }
    WindowWidthSizeClass.Medium -> {
        CWMNavigationType.NAVIGATION_RAIL to
            if (foldingDevicePosture != DevicePosture.NormalPosture) {
                CWMContentType.DUAL_PANE
            } else {
                CWMContentType.SINGLE_PANE
            }
    }
    WindowWidthSizeClass.Expanded -> {
        if (foldingDevicePosture is DevicePosture.BookPosture) {
            CWMNavigationType.NAVIGATION_RAIL
        } else {
            CWMNavigationType.PERMANENT_NAVIGATION_DRAWER
        } to
            CWMContentType.DUAL_PANE
    }
    else -> {
        CWMNavigationType.BOTTOM_NAVIGATION to
            CWMContentType.SINGLE_PANE
    }
}

private fun measureWindowsScreenHeight(windowSize: WindowSizeClass) =
    when (windowSize.heightSizeClass) {
        WindowHeightSizeClass.Compact -> {
            CWMNavigationContentPosition.TOP
        }
        WindowHeightSizeClass.Medium,
        WindowHeightSizeClass.Expanded -> {
            CWMNavigationContentPosition.CENTER
        }
        else -> {
            CWMNavigationContentPosition.TOP
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CWMNavigationWrapper(
    navigationType: CWMNavigationType,
    contentType: CWMContentType,
    displayFeature: List<DisplayFeature>,
    navigationContentPosition: CWMNavigationContentPosition
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        CWMNavigationActions(navController)
    }
    val navBackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination = navBackEntry?.destination?.route ?: START_DESTINATION

    if (navigationType == CWMNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(drawerContent = {
            if (routeIsNavigation(selectedDestination)) {
                PermanentNavigationDrawerContent(
                    selectedDestination = selectedDestination,
                    navigationContentPosition = navigationContentPosition,
                    navigateToTopLevelDestination = navigationActions::navigateTo
                )
            }
        }) {
            CWMAppContent(
                navigationType = navigationType,
                contentType = contentType,
                displayFeature = displayFeature,
                navigationContentPosition = navigationContentPosition,
                navController = navController,
                selectedDestination = selectedDestination,
                navigateToTopLevelDestination = navigationActions::navigateTo
            )
        }
    } else {
        ModalNavigationDrawer(
            drawerContent = {
                if (routeIsNavigation(selectedDestination)) {
                    ModalNavigationDrawerContent(
                        selectedDestination = selectedDestination,
                        navigationContentPosition = navigationContentPosition,
                        navigateToTopLevelDestination = navigationActions::navigateTo,
                        onDrawerClicked = {
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    )
                }
            },
            drawerState = drawerState
        ) {
            CWMAppContent(
                navigationType = navigationType,
                contentType = contentType,
                displayFeature = displayFeature,
                navigationContentPosition = navigationContentPosition,
                navController = navController,
                selectedDestination = selectedDestination,
                navigateToTopLevelDestination = navigationActions::navigateTo,
                onDrawerClicked = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            )
        }
    }
}

private fun routeIsNavigation(selectedDestination: String): Boolean {
    return selectedDestination in listOf(
        HOME_ROUTE,
        CALLING_ROUTE,
        STATUS_ROUTE,
        SETTINGS_ROUTE
    )
}

@Composable
fun CWMAppContent(
    modifier: Modifier = Modifier,
    navigationType: CWMNavigationType,
    contentType: CWMContentType,
    displayFeature: List<DisplayFeature>,
    navigationContentPosition: CWMNavigationContentPosition,
    navController: NavHostController,
    selectedDestination: String,
    navigateToTopLevelDestination: (CWMTopLevelDestination) -> Unit,
    onDrawerClicked: () -> Unit = {}
) {
    Row(modifier = modifier.fillMaxSize()) {
        if (routeIsNavigation(selectedDestination)) {
            AnimatedVisibility(visible = navigationType == CWMNavigationType.NAVIGATION_RAIL) {
                CWMNavigationRail(
                    selectedDestination = selectedDestination,
                    navigationContentPosition = navigationContentPosition,
                    navigateToTopLevelDestination = navigateToTopLevelDestination,
                    onDrawerClicked = onDrawerClicked
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            CWMNavHost(
                navController = navController,
                contentType = contentType,
                displayFeature = displayFeature,
                navigationType = navigationType,
                modifier = Modifier.weight(1f)
            )
            if (routeIsNavigation(selectedDestination)) {
                AnimatedVisibility(visible = navigationType == CWMNavigationType.BOTTOM_NAVIGATION) {
                    CWMBottomNavigationBar(
                        selectedDestination = selectedDestination,
                        navigateToTopLevelDestination = navigateToTopLevelDestination
                    )
                }
            }
        }
    }
}

@Composable
private fun CWMNavHost(
    navController: NavHostController,
    contentType: CWMContentType,
    displayFeature: List<DisplayFeature>,
    navigationType: CWMNavigationType,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = START_DESTINATION
    ) {
        loginScreen(
            contentType = contentType,
            navigationType = navigationType,
            navController = navController,
            displayFeature = displayFeature
        )
        registerScreen(
            contentType = contentType,
            navigationType = navigationType,
            navController = navController,
            displayFeature = displayFeature
        )
        homeScreen(
            contentType = contentType,
            navigationType = navigationType,
            navController = navController,
            displayFeature = displayFeature
        )
        statusScreen(
            contentType = contentType,
            navigationType = navigationType,
            navController = navController,
            displayFeature = displayFeature
        )
        callingScreen(
            contentType = contentType,
            navigationType = navigationType,
            navController = navController,
            displayFeature = displayFeature
        )
        settingsScreen(
            contentType = contentType,
            navigationType = navigationType,
            navController = navController,
            displayFeature = displayFeature
        )
        chatDetailScreen(
            contentType = contentType,
            navigationType = navigationType,
            navController = navController,
            displayFeature = listOf()
        )
    }
}
