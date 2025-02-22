package com.singularityindonesia.core.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavHostController

val CurrentDestination = compositionLocalOf { "" }

@Composable
fun DestinationProvider(
    navController: NavHostController,
    content: @Composable () -> Unit
) {
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

    CompositionLocalProvider(
        CurrentDestination provides currentDestination.value
    ) {
        content()
    }
}