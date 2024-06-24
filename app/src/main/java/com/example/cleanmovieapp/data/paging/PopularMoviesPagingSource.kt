package com.example.themovieapp.data.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cleanmovieapp.common.Constants.API_KEY
import com.example.cleanmovieapp.common.Constants.STARTING_PAGE_INDEX
import com.example.cleanmovieapp.data.service.MovieApi
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovie
import javax.inject.Inject

class PopularMoviesPagingSource @Inject constructor(
    private val api: MovieApi
) : PagingSource<Int, PopularMovie>() {
    override fun getRefreshKey(state: PagingState<Int, PopularMovie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovie> {
        val currentPage = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = api.getPopularMovies(page = currentPage, apiKey = API_KEY)
            val popularMovieList = response.toUIModel()
            LoadResult.Page(
                data = popularMovieList.movieList,
                prevKey = if (currentPage == STARTING_PAGE_INDEX) null else currentPage - 1,
                nextKey = if (popularMovieList.movieList.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}
