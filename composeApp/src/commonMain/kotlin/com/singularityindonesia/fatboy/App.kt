package com.singularityindonesia.fatboy

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.singularityindonesia.core.navigation.CurrentDestination
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
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

    MaterialTheme {
        CompositionLocalProvider(
            CurrentDestination provides currentDestination.value
        ) {
            Main(
                navController = navController
            )
        }
    }
}