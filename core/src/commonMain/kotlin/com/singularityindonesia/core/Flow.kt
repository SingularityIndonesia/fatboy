package com.singularityindonesia.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlin.time.Duration.Companion.minutes

fun<T> Flow<T>.oneMinuteState(
    scope: CoroutineScope,
    initial: T,
): StateFlow<T> = stateIn(scope, SharingStarted.WhileSubscribed(1.minutes), initial)