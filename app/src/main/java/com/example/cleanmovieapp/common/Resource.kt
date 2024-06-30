package com.example.cleanmovieapp.common

sealed class Resource<out T> {
    class Success<T>(val data: T) : Resource<T>()
    class Failure<T>(val error: Throwable) : Resource<T>()
    data object Loading : Resource<Nothing>()

}