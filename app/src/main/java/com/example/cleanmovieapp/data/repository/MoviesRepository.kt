package com.example.cleanmovieapp.data.repository

import com.example.cleanmovieapp.common.Constants.API_KEY
import com.example.cleanmovieapp.common.Resource
import com.example.cleanmovieapp.common.extension.remote
import com.example.cleanmovieapp.data.remote.popular_movies.HomeResponse
import com.example.cleanmovieapp.data.service.MovieApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val movieApi: MovieApi,
)  {
     fun getPopularMovies(): Flow<Resource<HomeResponse?>> {
        return flow {
            emit(movieApi.getPopularMovies(1,API_KEY))
        }.remote()
    }
}