package com.singularityindonesia.core

sealed class VMException : Exception() {
    class UnknownException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : VMException()

    class ApiException : VMException() {
        val errorCode: String = "TODO"
    }

    sealed class IOException : VMException() {
        class NetworkError : IOException()
    }

    sealed class IllegalState() : VMException() {
        class Null(
            override val message: String?,
            override val cause: Throwable? = NullPointerException(message)
        ) : IllegalState()
    }

    sealed class IllegalArgument : VMException() {
        class Null(
            override val message: String?,
            override val cause: Throwable? = NullPointerException(message)
        ) : IllegalArgument()
    }
}

fun toVmException(throwable: Throwable): VMException {
    return when {
        else -> VMException.UnknownException(throwable.message, throwable)
    }
}