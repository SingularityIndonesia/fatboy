package com.singularityindonesia.user

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle

val LocalUser = compositionLocalOf<UserRecord?> { error("User not provided") }

@Composable
fun UserRecordProvider(content: @Composable () -> Unit) {
    val user = remember { User.getInstance() }
    val record = user.record.collectAsStateWithLifecycle(null)

    CompositionLocalProvider(
        LocalUser provides record.value
    ) {
        content()
    }
}