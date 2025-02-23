package com.singularityindonesia.fatboy.ui.route

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

sealed interface HomeBottomNavigationRoute : Route {

    companion object {
        val menus = listOf(Route.Dashboard, Route.Profile)
    }

    @Composable
    fun Label() {
        Text(route.replaceFirstChar { it.uppercase() })
    }

    @Composable
    fun Icon()

}