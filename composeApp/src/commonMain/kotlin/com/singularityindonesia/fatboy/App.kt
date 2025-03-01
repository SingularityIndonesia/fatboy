package com.singularityindonesia.fatboy

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.singularityindonesia.core.navigation.DestinationProvider
import com.singularityindonesia.fatboy.ui.component.HeaderComponent1
import com.singularityindonesia.fatboy.ui.navigation.HomeBottomNavigator
import com.singularityindonesia.fatboy.ui.navigation.MainPlot
import com.singularityindonesia.fatboy.ui.route.Route
import com.singularityindonesia.user.UserRecordProvider
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    Provider(navController) {
        MaterialTheme {
            Scaffold(
                topBar = {
                    HeaderComponent1(
                        modifier = Modifier.statusBarsPadding(),
                        goToProfile = {
                            navController.navigate(Route.Profile.route)
                        }
                    )
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
        UserRecordProvider {
            content()
        }
    }
}