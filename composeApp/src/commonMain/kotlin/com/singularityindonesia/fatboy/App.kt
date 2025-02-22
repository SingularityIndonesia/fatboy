package com.singularityindonesia.fatboy

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.singularityindonesia.fatboy.ui.Root
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
//    val user = remember { User() }
    MaterialTheme {
        Root()
    }
}