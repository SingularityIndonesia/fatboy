package com.singularityindonesia.fatboy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.singularityindonesia.fatboy.ui.pane.Home
import com.singularityindonesia.fatboy.ui.pane.Profile
import com.singularityindonesia.fatboy.ui.route.Route

@Composable
fun MainPlot(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.Home.route,
    ) {
        composable(route = Route.Home.route) { Home() }
        composable(route = Route.Profile.route) { Profile() }
    }
}