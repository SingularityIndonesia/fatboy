package com.singularityindonesia.fatboy

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.singularityindonesia.fatboy.ui.navigation.HomeBottomNavigator
import com.singularityindonesia.fatboy.ui.navigation.MainPlot

@Composable
fun Main(navController: NavHostController) {
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