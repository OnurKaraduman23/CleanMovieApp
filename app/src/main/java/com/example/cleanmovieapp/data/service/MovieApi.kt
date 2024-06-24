package com.example.cleanmovieapp.data.service

import com.example.cleanmovieapp.common.Constants.QUERY_API_KEY
import com.example.cleanmovieapp.common.Constants.QUERY_PAGE
import com.example.cleanmovieapp.data.remote.popular_movies.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular?language=en-US")
    suspend fun getPopularMovies(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) apiKey: String
    ): PopularMoviesResponse
}