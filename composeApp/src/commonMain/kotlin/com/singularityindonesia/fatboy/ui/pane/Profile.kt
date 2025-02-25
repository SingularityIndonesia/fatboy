package com.singularityindonesia.fatboy.ui.pane

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.singularityindonesia.core.oneMinuteState
import com.singularityindonesia.user.LocalUser
import com.singularityindonesia.user.User


@Composable
fun Profile(
    modifier: Modifier = Modifier,
) {
    val userRecord = LocalUser.current

    LazyColumn(
        modifier = modifier,
    ) {
        item {
            Text(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, bottom = 16.dp),
                text = "Profile of, ${userRecord?.name ?: "..."}",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}