package com.singularityindonesia.fatboy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.singularityindonesia.fatboy.ui.pane.Dashboard
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
        startDestination = Route.Dashboard.route,
    ) {
        composable(route = Route.Dashboard.route) { Dashboard() }
        composable(route = Route.Profile.route) { Profile() }
    }
}