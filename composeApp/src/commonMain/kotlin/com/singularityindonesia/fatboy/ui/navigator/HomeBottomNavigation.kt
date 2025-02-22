package com.singularityindonesia.fatboy.ui.navigator

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.singularityindonesia.fatboy.ui.route.HomeBottomNavigationRoute
import com.singularityindonesia.fatboy.ui.route.Route
import kotlinx.coroutines.launch

@Composable
fun HomeBottomNavigation(
    navController: NavHostController,
    onItemClicked: (item: HomeBottomNavigationRoute) -> Unit
) {
    val scope = rememberCoroutineScope()
    val menus = remember { listOf(Route.Home, Route.Profile) }
    val currentDestination = remember { mutableStateOf("") }

    DisposableEffect(navController.currentDestination) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            currentDestination.value = destination.route.orEmpty()
        }

        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    BottomAppBar {
        menus.map {
            NavigationBarItem(
                selected = currentDestination.value == it.route,
                onClick = {
                    onItemClicked.invoke(it)
                },
                icon = {
                    it.Icon()
                },
                label = {
                    it.Label()
                },
            )
        }
    }
}