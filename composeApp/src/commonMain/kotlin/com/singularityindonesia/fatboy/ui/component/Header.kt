package com.singularityindonesia.fatboy.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.singularityindonesia.core.navigation.CurrentDestination
import com.singularityindonesia.fatboy.ui.route.Route
import com.singularityindonesia.user.LocalUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderComponent1(
    modifier: Modifier = Modifier,
    goToProfile: () -> Unit,
) {
    val user = LocalUser.current
    val currentDestination = CurrentDestination.current

    Row(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 16.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // fixme: remove later, prefer using appbar controll
            if (currentDestination == Route.Dashboard.route)
                Text(
                    text = "Wellcome home ${user?.name?.capitalize() ?: "..."}",
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            Text(
                text = currentDestination.capitalize(),
                style = MaterialTheme.typography.titleLarge,
            )
        }

        Row(
            modifier = Modifier
        ) {
            // fixme: remove later, prefer using appbar controll
            if (currentDestination == Route.Dashboard.route)
                Icon(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .clickable { goToProfile.invoke() },
                    imageVector = Icons.Rounded.Person,
                    contentDescription = null
                )
        }
    }
}