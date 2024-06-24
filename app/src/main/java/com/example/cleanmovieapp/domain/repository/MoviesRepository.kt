package com.example.cleanmovieapp.domain.repository

import androidx.paging.PagingData
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getPopularMovies(): Flow<PagingData<PopularMovie>>
}