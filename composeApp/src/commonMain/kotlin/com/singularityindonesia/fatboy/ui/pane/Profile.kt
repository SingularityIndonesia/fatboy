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
import com.singularityindonesia.user.User
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.stateIn

class ProfileViewModel(
    private val user: User = User.getInstance()
) : ViewModel() {
    val userRecord = user.record.stateIn(viewModelScope, WhileSubscribed(0L), null)
}

@Composable
fun Profile(
    modifier: Modifier = Modifier,
    vm: ProfileViewModel = viewModel { ProfileViewModel() }
) {
    val userRecord by vm.userRecord.collectAsStateWithLifecycle()

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