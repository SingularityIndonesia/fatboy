package com.singularityindonesia.core.presentation

import androidx.compose.runtime.compositionLocalOf
import com.singularityindonesia.core.user.User

val LocalUser = compositionLocalOf<User?> { error("User is not provided") }