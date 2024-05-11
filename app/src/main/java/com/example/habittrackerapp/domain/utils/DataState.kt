package com.example.habittrackerapp.domain.utils

sealed class DataState<T> (
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>() : DataState<T>()
    class Success<T>(data: T?) : DataState<T>()
    class Error<T>(message: String?) : DataState<T>()
}