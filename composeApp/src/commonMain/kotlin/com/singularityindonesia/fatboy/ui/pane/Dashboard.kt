package com.singularityindonesia.fatboy.ui.pane

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.singularityindonesia.fatboy.ui.component.MediumCard
import com.singularityindonesia.user.LocalUser
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Dashboard() {
    val user = LocalUser.current
    val density = LocalDensity.current
    val panelSize = remember{ mutableStateOf(IntSize.Zero) }
    val gridCells = remember(panelSize.value) {
        val quarterDp = ((panelSize.value.width / density.density) - (16 * 5)).div(4f).toInt().dp
        val quarterContentSize = if (quarterDp <= 0.dp) 10.dp else quarterDp
        val cel = GridCells.Adaptive(quarterContentSize)
        derivedStateOf { cel }
    }
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged {
                panelSize.value = it
            },
        state = gridState,
        columns = gridCells.value,
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item(
            span = { GridItemSpan(4) }
        ) {
            Box(
                modifier = Modifier.padding(
                    vertical = 16.dp
                )
            ) {
                Text(
                    text = "Wellcome home, ${user?.name ?: "..."}",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
        repeat(11) { index ->
            item(
                span = { GridItemSpan(2) }
            ) {
                MediumCard(
                    modifier = Modifier.height(200.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text("C$index")
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Dashboard()
}