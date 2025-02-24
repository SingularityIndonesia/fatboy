package com.singularityindonesia.user

import com.singularityindonesia.core.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class User(
    private val coroutine: CoroutineScope = CoroutineScope(Dispatchers.IO),
    private val dataSource: Any = Any(),
) {
    companion object {
        private var instance: User? = null
        fun getInstance(): User {
            return instance ?: User().also { instance = it }
        }
    }

    private val _state = mutableVMStateFlow<UserRecord>()

    @OptIn(ExperimentalStdlibApi::class)
    val state = _state.onInit { fetch() }

    @OptIn(ExperimentalStdlibApi::class)
    val record = state.catchSuccess { it.data }

    private var fetchingJob: Job? = null

    fun fetch(): Job {
        fetchingJob?.cancel()

        fetchingJob = coroutine.launch {
            _state.update { loading() }
            delay(3000)
            val response = UserRecord(
                id = "Atma",
                name = "Singularity",
            )
            _state.update { success(response) }
        }.handleException { e ->
            _state.update { error(e) }
        }

        return fetchingJob!!
    }
}