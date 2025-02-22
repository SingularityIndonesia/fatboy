package com.singularityindonesia.fatboy.ui.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.singularityindonesia.core.navigation.CurrentDestination
import com.singularityindonesia.fatboy.ui.route.HomeBottomNavigationRoute
import com.singularityindonesia.fatboy.ui.route.Route

@Composable
fun HomeBottomNavigator(
    onItemClicked: (item: HomeBottomNavigationRoute) -> Unit
) {
    val menus = remember { listOf(Route.Home, Route.Profile) }

    BottomAppBar {
        menus.map {
            NavigationBarItem(
                selected = CurrentDestination.current == it.route,
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