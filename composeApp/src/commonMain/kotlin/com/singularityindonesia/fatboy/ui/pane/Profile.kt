package com.singularityindonesia.fatboy.ui.pane

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.singularityindonesia.fatboy.ui.component.CardWrapper
import com.singularityindonesia.user.LocalUser
import com.singularityindonesia.user.UserRecord


@Composable
fun Profile(
    modifier: Modifier = Modifier,
) {
    val userRecord = LocalUser.current

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp)
    ) {
        item(key = 0) {
            ProfileHeader(user = userRecord)
        }
        item(key = 1) {
            ProfileCardMenu(
                title = "Inventories",
                items = listOf(
                    ProfileCardItem(
                        title = "My stories",
                        icon = Icons.Rounded.Place,
                        action = {}
                    ),
                    ProfileCardItem(
                        title = "Support",
                        icon = Icons.Rounded.Phone,
                        action = {}
                    )
                )
            )
            ProfileCardMenu(
                title = "Preferences",
                items = listOf(
                    ProfileCardItem(
                        title = "Push notification",
                        icon = Icons.Rounded.Person,
                        haveSwitchButton = true,
                        switchButtonChecked = { },
                        action = {}
                    ),
                    ProfileCardItem(
                        title = "Face ID",
                        icon = Icons.Rounded.Person,
                        haveSwitchButton = true,
                        switchButtonChecked = { },
                        action = {}
                    ),
                    ProfileCardItem(
                        title = "PIN code",
                        icon = Icons.Rounded.Person,
                        action = {}
                    ),
                    ProfileCardItem(
                        title = "Logout",
                        icon = Icons.Rounded.Close,
                        redAccent = true,
                        action = {}
                    )
                )
            )
        }
    }
}

@Composable
private fun ProfileHeader(
    user: UserRecord?,
    action: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier
                .size(96.dp)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = CircleShape
                )
                .padding(8.dp),
            imageVector = Icons.Rounded.Person,
            contentDescription = null,
        )
        Text(
            text = user?.name ?: "Anonymous",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "profile@mail.com",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Button(
            modifier = Modifier.wrapContentSize(),
            onClick = { action() }
        ) {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = "Edit profile",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Immutable
data class ProfileCardItem(
    val title: String,
    val icon: ImageVector, // Could be replaced with local icon
    val redAccent: Boolean = false,
    val haveSwitchButton: Boolean = false,
    val switchButtonChecked: (Boolean) -> Unit = {},
    val action: () -> Unit = {}
)

@Composable
private fun ProfileCardMenu(
    title: String,
    items: List<ProfileCardItem>,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        text = title,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
    CardWrapper {
        items.map { item ->
            ProfileCardMenuItem(
                title = item.title,
                icon = item.icon,
                isRedAccent = item.redAccent,
                haveSwitchButton = item.haveSwitchButton,
                switchButtonChecked = true,
                action = item.action
            )
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 12.dp),
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
            )
        }
    }
}


@Composable
private fun ProfileCardMenuItem(
    title: String,
    icon: ImageVector,
    isRedAccent: Boolean = false,
    haveSwitchButton: Boolean = false,
    switchButtonChecked: Boolean = false,
    action: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { action() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Icon(
                modifier = Modifier
                    .size(36.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = ShapeDefaults.Medium,
                    )
                    .padding(4.dp),
                imageVector = icon,
                contentDescription = null,
            )
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelLarge,
                        color =
                            if (isRedAccent) MaterialTheme.colorScheme.error
                            else MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Start,
                    )

                    if (haveSwitchButton) {
                        Switch(
                            checked = switchButtonChecked,
                            onCheckedChange = null
                        )
                    } else {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}
