package com.example.cleanmovieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.cleanmovieapp.data.service.MovieApi
import com.example.cleanmovieapp.domain.repository.MoviesRepository
import com.example.themovieapp.data.paging.PopularMoviesPagingSource
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MoviesRepository {
    override suspend fun getPopularMovies(): Flow<PagingData<PopularMovie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PopularMoviesPagingSource(api = movieApi)
            }
        ).flow
    }
}