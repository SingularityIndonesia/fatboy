package com.singularityindonesia.fatboy.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.bundle.Bundle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.rememberNavController
import com.singularityindonesia.fatboy.ui.navigator.HomeBottomNavigation
import com.singularityindonesia.fatboy.ui.plot.RootPlot
import kotlinx.coroutines.launch

@Composable
fun Root() {
    val navController = rememberNavController()
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

    Scaffold(
        topBar = {

        },
        bottomBar = {
            HomeBottomNavigation(
                navController = navController,
                onItemClicked = {
                    navController.navigate(route = it.route)
                }
            )
        }
    ) { padding ->
        RootPlot(
            modifier = Modifier.padding(padding),
            navController = navController
        )
    }
}