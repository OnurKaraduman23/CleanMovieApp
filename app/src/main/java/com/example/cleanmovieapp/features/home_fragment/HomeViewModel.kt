package com.example.cleanmovieapp.features.home_fragment


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanmovieapp.common.Status
import com.example.cleanmovieapp.common.ThrowableReporter
import com.example.cleanmovieapp.common.extension.onEachError
import com.example.cleanmovieapp.common.extension.onEachLoading
import com.example.cleanmovieapp.common.extension.onEachSuccess
import com.example.cleanmovieapp.common.extension.safeLaunchIn
import com.example.cleanmovieapp.domain.use_case.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    ) : ViewModel() {

    private val _pageViewState =
        MutableStateFlow<HomePageViewState?>(null)
    val pageViewState: StateFlow<HomePageViewState?> get() = _pageViewState


    private val _statusViewState =
        MutableStateFlow<HomeStatusViewState?>(null)
    val statusViewState: StateFlow<HomeStatusViewState?> get() = _statusViewState


    init {
        getPopularMovies()
    }

    fun getPopularMovies() {
        getPopularMoviesUseCase.getPopularMovies()
            .onEachSuccess {
                _pageViewState.value = HomePageViewState(it)
                _statusViewState.value = HomeStatusViewState(Status.SUCCESS)
            }
            .onEachLoading {
                _statusViewState.value = HomeStatusViewState(Status.LOADING)
            }
            .onEachError {
                ThrowableReporter.setReport(it) // bunu burada kullanmak doğru mu ?
                _statusViewState.value = HomeStatusViewState(Status.ERROR)

            }
            .safeLaunchIn(viewModelScope)
    }
}

