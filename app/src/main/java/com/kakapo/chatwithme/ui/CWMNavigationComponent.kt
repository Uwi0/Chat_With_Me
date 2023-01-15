package com.kakapo.chatwithme.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuOpen
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import com.kakapo.chatwithme.R
import com.kakapo.ui.utils.CWMNavigationContentPosition

@Composable
fun CWMNavigationRail(
    selectedDestination: String,
    navigationContentPosition: CWMNavigationContentPosition,
    navigateToTopLevelDestination: (CWMTopLevelDestination) -> Unit,
    onDrawerClicked: () -> Unit = {}
) {
    NavigationRail(
        modifier = Modifier.fillMaxHeight(),
        containerColor = MaterialTheme.colorScheme.inverseOnSurface
    ) {
        Layout(
            modifier = Modifier.widthIn(max = 80.dp),
            content = {
                Column(
                    modifier = Modifier.layoutId(LayoutType.HEADER),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    NavigationRailItem(
                        selected = false,
                        onClick = onDrawerClicked,
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = stringResource(id = R.string.menu)
                            )
                        }
                    )
                    Spacer(Modifier.height(8.dp)) // NavigationRailHeaderPadding
                    Spacer(Modifier.height(4.dp))
                }

                Column(
                    modifier = Modifier.layoutId(LayoutType.CONTENT),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    TOP_LEVEL_DESTINATIONS.forEach { cwmDestination ->
                        NavigationRailItem(
                            selected = selectedDestination == cwmDestination.route,
                            onClick = { navigateToTopLevelDestination(cwmDestination) },
                            icon = {
                                Icon(
                                    imageVector = cwmDestination.selectedIcon,
                                    contentDescription = stringResource(id = cwmDestination.iconTextId)
                                )
                            }
                        )
                    }
                }
            },
            measurePolicy = { measurable, constraint ->
                lateinit var headerMeasurable: Measurable
                lateinit var contentMeasurable: Measurable
                measurable.forEach {
                    when (it.layoutId) {
                        LayoutType.HEADER -> headerMeasurable = it
                        LayoutType.CONTENT -> contentMeasurable = it
                        else -> error("Unknown layout id encountered")
                    }
                }
                val headerPlaceable = headerMeasurable.measure(constraint)
                val contentPlaceable = contentMeasurable.measure(
                    constraint.offset(vertical = -headerPlaceable.height)
                )
                layout(constraint.maxWidth, constraint.maxHeight) {
                    headerPlaceable.placeRelative(0, 0)
                    val nonContentVerticalSpace = constraint.maxHeight - contentPlaceable.height
                    val contentPlaceableY = when (navigationContentPosition) {
                        CWMNavigationContentPosition.TOP -> 0
                        CWMNavigationContentPosition.CENTER -> nonContentVerticalSpace / 2
                    }.coerceAtLeast(headerPlaceable.height)
                    contentPlaceable.placeRelative(0, contentPlaceableY)
                }
            }
        )
    }
}

@Composable
fun CWMBottomNavigationBar(
    selectedDestination: String,
    navigateToTopLevelDestination: (CWMTopLevelDestination) -> Unit
) {
    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        TOP_LEVEL_DESTINATIONS.forEach { cwmDestination ->
            NavigationBarItem(
                selected = selectedDestination == cwmDestination.route,
                onClick = { navigateToTopLevelDestination(cwmDestination) },
                icon = {
                    Icon(
                        imageVector = cwmDestination.selectedIcon,
                        contentDescription = stringResource(cwmDestination.iconTextId)
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermanentNavigationDrawerContent(
    selectedDestination: String,
    navigationContentPosition: CWMNavigationContentPosition,
    navigateToTopLevelDestination: (CWMTopLevelDestination) -> Unit
) {
    PermanentDrawerSheet(modifier = Modifier.sizeIn(minWidth = 200.dp, maxWidth = 300.dp)) {
        Layout(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .padding(16.dp),
            content = {
                Column(
                    modifier = Modifier.layoutId(LayoutType.HEADER),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = stringResource(id = com.kakapo.designsystem.R.string.app_name).uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Column(
                    modifier = Modifier
                        .layoutId(LayoutType.CONTENT)
                        .verticalScroll(
                            rememberScrollState()
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TOP_LEVEL_DESTINATIONS.forEach { cwmDestination ->
                        NavigationDrawerItem(
                            selected = selectedDestination == cwmDestination.route,
                            label = {
                                Text(
                                    text = stringResource(id = cwmDestination.iconTextId),
                                    modifier = Modifier.padding(16.dp)
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = cwmDestination.selectedIcon,
                                    contentDescription = stringResource(id = cwmDestination.iconTextId)
                                )
                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = Color.Transparent
                            ),
                            onClick = { navigateToTopLevelDestination(cwmDestination) })
                    }
                }
            },
            measurePolicy = { measurables, constraints ->
                lateinit var headerMeasurable: Measurable
                lateinit var contentMeasurable: Measurable
                measurables.forEach {
                    when (it.layoutId) {
                        LayoutType.HEADER -> headerMeasurable = it
                        LayoutType.CONTENT -> contentMeasurable = it
                        else -> error("Unknown layoutId encountered!")
                    }
                }

                val headerPlaceable = headerMeasurable.measure(constraints)
                val contentPlaceable = contentMeasurable.measure(
                    constraints.offset(vertical = -headerPlaceable.height)
                )
                layout(constraints.maxWidth, constraints.maxHeight) {
                    headerPlaceable.placeRelative(0, 0)
                    val nonContentVerticalSpace = constraints.maxHeight - contentPlaceable.height
                    val contentPlaceableY = when (navigationContentPosition) {
                        CWMNavigationContentPosition.TOP -> 0
                        CWMNavigationContentPosition.CENTER -> nonContentVerticalSpace / 2
                    }.coerceAtLeast(headerPlaceable.height)
                    contentPlaceable.placeRelative(0, contentPlaceableY)
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalNavigationDrawerContent(
    selectedDestination: String,
    navigationContentPosition: CWMNavigationContentPosition,
    navigateToTopLevelDestination: (CWMTopLevelDestination) -> Unit,
    onDrawerClicked: () -> Unit
) {
    ModalDrawerSheet {
        Layout(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .padding(16.dp),
            content = {
                Column(
                    modifier = Modifier.layoutId(LayoutType.HEADER),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = com.kakapo.designsystem.R.string.app_name).uppercase(),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        IconButton(onClick = onDrawerClicked) {
                            Icon(
                                imageVector = Icons.Default.MenuOpen,
                                contentDescription = stringResource(id = R.string.navigation_drawer)
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .layoutId(LayoutType.CONTENT)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TOP_LEVEL_DESTINATIONS.forEach { cwmDestination ->
                        NavigationDrawerItem(
                            selected = selectedDestination == cwmDestination.route,
                            label = {
                                Text(
                                    text = stringResource(id = cwmDestination.iconTextId),
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = cwmDestination.selectedIcon,
                                    contentDescription = stringResource(
                                        id = cwmDestination.iconTextId
                                    )
                                )
                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = Color.Transparent
                            ),
                            onClick = { navigateToTopLevelDestination(cwmDestination) }
                        )
                    }
                }
            },
            measurePolicy = { measurables, constraints ->
                layout(constraints.maxWidth, constraints.maxHeight) {
                    lateinit var headerMeasurable: Measurable
                    lateinit var contentMeasurable: Measurable
                    measurables.forEach {
                        when (it.layoutId) {
                            LayoutType.HEADER -> headerMeasurable = it
                            LayoutType.CONTENT -> contentMeasurable = it
                            else -> error("Unknown layoutId encountered!")
                        }
                    }

                    val headerPlaceable = headerMeasurable.measure(constraints)
                    val contentPlaceable = contentMeasurable.measure(
                        constraints.offset(vertical = -headerPlaceable.height)
                    )
                    headerPlaceable.placeRelative(0, 0)
                    val nonContentVerticalSpace = constraints.maxHeight - contentPlaceable.height
                    val contentPlaceableY = when (navigationContentPosition) {
                        CWMNavigationContentPosition.TOP -> 0
                        CWMNavigationContentPosition.CENTER -> nonContentVerticalSpace / 2
                    }.coerceAtLeast(headerPlaceable.height)
                    contentPlaceable.placeRelative(0, contentPlaceableY)
                }
            }
        )
    }
}

enum class LayoutType {
    HEADER, CONTENT
}