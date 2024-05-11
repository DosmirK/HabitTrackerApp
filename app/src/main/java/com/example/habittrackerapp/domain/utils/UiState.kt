package com.example.habittrackerapp.domain.utils

sealed class UiState<T> (
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>() : UiState<T>()
    class Success<T>(data: T?) : UiState<T>()
    class Empty<T>(): UiState<T>()
    class Error<T>(message: String?) : UiState<T>()
}