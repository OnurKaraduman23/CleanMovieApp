package com.example.cleanmovieapp.domain.use_case

import com.example.cleanmovieapp.data.remote.popular_movies.PopularMovieResponse
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovie
import java.util.Locale
import javax.inject.Inject

class MovieListMapper @Inject constructor() {

    // Burada voteAvarage formatlanacak
    fun map(responseList: List<PopularMovieResponse?>?): List<PopularMovie> {
        return responseList?.mapNotNull { result ->
            if (result?.id == null) return@mapNotNull null
            PopularMovie(
                id = result.id,
                title = result.title.orEmpty(),
                overview = result.overview.orEmpty(),
                voteAverage = (String.format(Locale.US,"%,.1f",result.voteAverage).toDouble() ),
                posterPath = result.posterPath.orEmpty()
            )
        }.orEmpty()
    }
}