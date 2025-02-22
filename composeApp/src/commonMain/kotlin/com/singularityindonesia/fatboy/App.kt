package com.singularityindonesia.fatboy

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.singularityindonesia.core.navigation.DestinationProvider
import com.singularityindonesia.fatboy.ui.navigation.HomeBottomNavigator
import com.singularityindonesia.fatboy.ui.navigation.MainPlot
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    MaterialTheme {
        Provider(navController) {
            Scaffold(
                topBar = {

                },
                bottomBar = {
                    HomeBottomNavigator(
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
    }
}

/**
 * Provides everything here
 */
@Composable
private fun Provider(
    navController: NavHostController,
    content: @Composable () -> Unit
) {
    DestinationProvider(navController) {
        content()
    }
}