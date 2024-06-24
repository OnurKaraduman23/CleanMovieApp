package com.example.cleanmovieapp.features.home_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.cleanmovieapp.domain.use_case.GetPopularMoviesUseCase
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase

) : ViewModel() {

    private val _popularMovieList = MutableStateFlow<PagingData<PopularMovie>>(PagingData.empty())
    val popularMovieList: StateFlow<PagingData<PopularMovie>> get() = _popularMovieList

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase.getPopularMovies()
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _popularMovieList.value = pagingData
                }
        }
    }


}