package com.example.cleanmovieapp.domain.model.popular_movies


import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovie

data class HomeContent(
    val page: Int,
    val movies: List<PopularMovie>,
    val totalPages: Int,
    val totalResults: Int
)