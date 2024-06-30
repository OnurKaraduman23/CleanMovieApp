package com.example.cleanmovieapp.data.remote.popular_movies


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose


data class HomeResponse(
    @SerializedName("page")
    @Expose
    val page: Int?,
    @SerializedName("results")
    @Expose
    val movies: List<PopularMovieResponse?>,
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int?,
    @SerializedName("total_results")
    @Expose
    val totalResults: Int?
)