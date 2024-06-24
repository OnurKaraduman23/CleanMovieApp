package com.example.themovieapp.domain.model.ui_model.popular_movies

data class PopularMovie(
    val id: Int,
    val title: String,
    val overview: String,
    val voteAverage: Double,
    val posterPath: String
)
