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

    private val _state = MutableStateFlow<VMState<UserRecord>>(VMState.Initial)
    val state = _state
        .onStart {
            if (_state.value is VMState.Initial)
                fetch()
        }

    val record = state
        .filterIsInstance<Result.Success<UserRecord>>()
        .map { it.data }

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
        }.apply {
            invokeOnCompletion { e ->
                if (e == null) return@invokeOnCompletion
                val exception = toVmException(e)
                _state.update {
                    error(exception = exception)
                }
            }
        }

        return fetchingJob!!
    }
}