package com.example.cleanmovieapp.common.extension

import com.example.cleanmovieapp.common.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

fun <T> tryFlowOrEmitError(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    block: suspend () -> T,
) = flow {
    try {
        emit(Resource.Success(block()))
    }  catch (e: HttpException) {
        emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
    } catch (e: IOException) {
        emit(Resource.Error("Couldn't reach server. Check your internet connection."))
    }
}.flowOn(dispatcher)