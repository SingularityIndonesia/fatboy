package com.singularityindonesia.core

import kotlinx.coroutines.flow.*

sealed interface VMState<out T> {

    data object Initial : VMState<Nothing>

    data object Loading : VMState<Nothing>

}

sealed interface Result<out T> : VMState<T> {

    data class Success<T>(
        val data: T
    ) : Result<T>

    data class Error(
        val error: VMException
    ) : Result<Nothing>
}

// region Utils
fun initial() = VMState.Initial
fun loading() = VMState.Loading
fun <T> success(data: T) = Result.Success(data)
fun error(exception: VMException) = Result.Error(exception)

fun <T> lift(error: VMException?, data: T?): Result<T> {
    return if (error != null) {
        Result.Error(error)
    } else if (data == null) {
        Result.Error(error = VMException.IllegalState.Null(message = "data is null"))
    } else {
        Result.Success(data)
    }
}

fun <T, R> VMState<T>.map(transformer: (T) -> R): VMState<R> {
    return when (this) {
        is VMState.Initial -> this
        is VMState.Loading -> this
        is Result.Error -> this
        is Result.Success<T> -> Result.Success(transformer.invoke(this.data))
    }
}

fun <T> VMState<T>.flatMapError(transformer: (Exception?) -> VMState<@UnsafeVariance T>): VMState<T> {
    return when (this) {
        is VMState.Initial -> this
        is VMState.Loading -> this
        is Result.Error -> transformer.invoke(this.error)
        is Result.Success -> this
    }
}

fun <T> VMState<T>.onSuccess(bloc: (T) -> Unit): VMState<T> {
    return this.also {
        if (this is Result.Success) {
            bloc.invoke(data)
        }
    }
}

inline fun <reified T> StateFlow<VMState<T>>.onInit(crossinline bloc: () -> Unit): Flow<VMState<T>> {
    return this.onStart {
        if (value is VMState.Initial)
            bloc()
    }
}

inline fun <reified T, R> Flow<VMState<T>>.selectSuccess(crossinline map: (Result.Success<T>) -> R): Flow<R> {
    return this.filterIsInstance<Result.Success<T>>()
        .map(map)
}

fun <T> mutableVMStateFlow(): MutableStateFlow<VMState<T>> = MutableStateFlow(initial())

// endregion