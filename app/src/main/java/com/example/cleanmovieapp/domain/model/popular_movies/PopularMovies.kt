package com.example.themovieapp.domain.model.ui_model.popular_movies


data class PopularMovies(

    val page: Int,
    val movieList: List<PopularMovie>,
)