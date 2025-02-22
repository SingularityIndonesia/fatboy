package com.singularityindonesia.fatboy

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.singularityindonesia.core.navigation.DestinationProvider
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    MaterialTheme {
        DestinationProvider(navController) {
            Main(
                navController = navController
            )
        }
    }
}