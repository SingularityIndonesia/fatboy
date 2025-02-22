package com.singularityindonesia.fatboy

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.singularityindonesia.fatboy.ui.navigation.HomeBottomNavigator
import com.singularityindonesia.fatboy.ui.navigation.MainPlot

@Composable
fun Main() {
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
            HomeBottomNavigator(
                navController = navController,
                onItemClicked = {
                    navController.navigate(route = it.route)
                }
            )
        }
    ) { padding ->
        MainPlot(
            modifier = Modifier.padding(padding),
            navController = navController
        )
    }
}