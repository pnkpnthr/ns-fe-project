package com.example.ns_fe_project.model

sealed class State<out T> {
    object Loading: State<Nothing>()
    data class Success<out T>(val data: T): State<T>()
    data class Failed(val error: Throwable): State<Nothing>()

    val isLoading get() = this is Loading
    val valueOrNull get() = (this as Success?)?.data
    val isFailed get() = this is Failed
}