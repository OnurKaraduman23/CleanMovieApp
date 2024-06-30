package com.example.cleanmovieapp.domain.use_case

import com.example.cleanmovieapp.common.Resource
import com.example.cleanmovieapp.common.extension.mapOnData
import com.example.cleanmovieapp.data.repository.MoviesRepository
import com.example.cleanmovieapp.domain.model.popular_movies.HomeContent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val mapper: HomeContentMapper
) {
    fun getPopularMovies(): Flow<Resource<HomeContent>> {
      return moviesRepository.getPopularMovies().mapOnData{
           mapper.map(it)
       }
    }


}

