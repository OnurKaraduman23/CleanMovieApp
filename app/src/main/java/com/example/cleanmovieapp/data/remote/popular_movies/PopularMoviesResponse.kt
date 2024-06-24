package com.example.cleanmovieapp.data.remote.popular_movies


import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovies
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose


data class PopularMoviesResponse(
    @SerializedName("page")
    @Expose
    val page: Int,
    @SerializedName("results")
    @Expose
    val results: List<PopularMovieResult>,
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int,
    @SerializedName("total_results")
    @Expose
    val totalResults: Int
) {
    fun toUIModel(): PopularMovies {
        return PopularMovies(
            page = this.page,
            movieList = this.results.map { it.toUIModel() }
        )
    }
}