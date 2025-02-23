package com.singularityindonesia.fatboy.ui.navigation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.singularityindonesia.core.navigation.CurrentDestination
import com.singularityindonesia.fatboy.ui.route.HomeBottomNavigationRoute
import com.singularityindonesia.fatboy.ui.route.Route

@Composable
fun HomeBottomNavigator(
    onItemClicked: (item: HomeBottomNavigationRoute) -> Unit
) {
    val menus = remember { HomeBottomNavigationRoute.menus }

    BottomAppBar(
        modifier = Modifier.clip(
            RoundedCornerShape(16.dp,16.dp,0.dp,0.dp)
        ),
        containerColor = Color.White,
    ) {
        menus.map {
            NavigationBarItem(
                selected = CurrentDestination.current == it.route,
                onClick = {
                    onItemClicked.invoke(it)
                },
                icon = {
                    it.Icon()
                },
                label = {
                    it.Label()
                },
                colors = NavigationBarItemDefaults.colors().copy(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    // fixme: use system color
                    selectedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}