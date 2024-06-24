package com.example.cleanmovieapp.domain.use_case

import androidx.paging.PagingData
import com.example.cleanmovieapp.di.IoDispatcher
import com.example.cleanmovieapp.domain.repository.MoviesRepository
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getPopularMovies(): Flow<PagingData<PopularMovie>> =
        moviesRepository.getPopularMovies()
            .flowOn(dispatcher)
}