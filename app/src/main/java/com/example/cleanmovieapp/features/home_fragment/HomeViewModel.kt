package com.example.cleanmovieapp.features.home_fragment

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.cleanmovieapp.common.Resource
import com.example.cleanmovieapp.domain.use_case.GetPopularMoviesUseCase
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    application: Application

) : AndroidViewModel(application) {

    private val _popularMovieList =
        MutableStateFlow<Resource<PagingData<PopularMovie>>>(Resource.Loading())
    val popularMovieList: StateFlow<Resource<PagingData<PopularMovie>>> get() = _popularMovieList

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            if (hasInternetConnection()) {
                _popularMovieList.value = Resource.Loading()
                val result = runCatching {
                    getPopularMoviesUseCase.getPopularMovies()
                        .cachedIn(viewModelScope)
                        .collectLatest { pagingData ->
                            _popularMovieList.value = Resource.Success(pagingData)
                        }
                }
                result.onFailure { e ->
                    _popularMovieList.value = Resource.Error(
                        e.message ?: "Unknown Error"
                    ) // İnternet bağlantısı dışındaki hata
                }
            } else {
                _popularMovieList.value = Resource.Error("No internet connection")
            }
        }
    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }


}