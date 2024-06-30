package com.example.cleanmovieapp.domain.use_case

import com.example.cleanmovieapp.data.remote.popular_movies.HomeResponse
import com.example.cleanmovieapp.domain.model.popular_movies.HomeContent
import javax.inject.Inject

class HomeContentMapper  @Inject constructor(
    private val mapper: MovieListMapper
) {
    fun map(response: HomeResponse?): HomeContent {
        return HomeContent(
            page = response?.page ?: 1,
            movies = mapper.map(response?.movies),
            totalPages = response?.totalPages ?: 1,
            totalResults = response?.totalResults ?: 1
        )
    }
}