package com.example.cleanmovieapp.common.extension

import com.example.cleanmovieapp.common.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

fun <T> Flow<T>.safeLaunchIn(scope: CoroutineScope): Job = scope.launch {
    catch {
//        ThrowableReporter.report(it) //Hata raporlama mekanizması
    }.collect()
}

fun <T> Flow<Resource<T>>.onEachLoading(onLoading: suspend () -> Unit = {}): Flow<Resource<T>> =
    onEach {
        if (it is Resource.Loading) {
            onLoading.invoke()
        }
    }

fun <T> Flow<Resource<T>>.onEachError(onError: suspend (Throwable) -> Unit = {}): Flow<Resource<T>> =
    onEach {
        if (it is Resource.Failure) {
            onError.invoke(Throwable(it.error.toString()))
//            ThrowableReporter.setReport(Throwable(it.error.message))
        }
    }

fun <T> Flow<Resource<T>>.onEachSuccess(onSuccess: suspend (T) -> Unit = {}): Flow<Resource<T>> =
    onEach {
        if (it is Resource.Success) {
            onSuccess.invoke(it.data)
        }
    }

inline fun <T, R> Flow<Resource<T>>.mapOnData(crossinline transform: (T) -> R): Flow<Resource<R>> {
    return map { resource ->
        when (resource) {
            is Resource.Success -> {
                Resource.Success(transform(resource.data))
            }

            is Resource.Loading -> {
                Resource.Loading
            }

            is Resource.Failure -> {
                Resource.Failure(resource.error)
            }
        }
    }
}

fun <T> Flow<T>.remote(withLoading: Boolean = true): Flow<Resource<T>> {
    return map<T, Resource<T>> { Resource.Success(it) }
        .onStart { if (withLoading) emit(Resource.Loading) }
        .catch { cause ->
            emit(Resource.Failure(cause))
        }
}