package com.singularityindonesia.fatboy.ui.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

sealed interface Route {
    val route: String

    @Serializable
    data object Dashboard: HomeBottomNavigationRoute {

        override val route: String = "dashboard"

        @Composable
        override fun Icon() {
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = null
            )
        }
    }

    @Serializable
    data object Profile : Route, HomeBottomNavigationRoute {
        override val route: String = "profile"

        @Composable
        override fun Icon() {
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = null
            )
        }
    }
}