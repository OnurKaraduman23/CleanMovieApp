package com.example.cleanmovieapp.features.home_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.cleanmovieapp.common.extension.setVisibility
import com.example.cleanmovieapp.databinding.FragmentHomeBinding
import com.example.cleanmovieapp.features.home_fragment.adapter.PopularMoviesAdapter
import com.example.moviecaseapp.common.binding_adapter.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPopularMoviesRecyclerView()
        collectViewModel()
        setClickListeners()

    }

    private fun setClickListeners(){
        binding.statusViewHome.setErrorClickListener {
            homeViewModel.getPopularMovies()
        }
    }

    private fun setupPopularMoviesRecyclerView() {
        popularMoviesAdapter = PopularMoviesAdapter()
        binding.popularMoviesRecyclerView.adapter = popularMoviesAdapter
    }

    private fun collectViewModel() {
        with(homeViewModel) {
            lifecycleScope.launch {
                pageViewState.collectLatest { state ->
                    Log.e("Dante", state?.content?.movies?.map { it.title }.toString())
                    state?.let { renderPageViewState(it) }
                }
            }
            lifecycleScope.launch {
                statusViewState.collectLatest { state ->
                    state?.let { renderStatusViewState(it) }
                }
            }
        }
    }

    private fun renderPageViewState(viewState: HomePageViewState) {
        popularMoviesAdapter.submitList(viewState.content.movies)
    }

    private fun renderStatusViewState(statusViewState: HomeStatusViewState){
        binding.statusViewHome.setViewState(statusViewState)
        binding.popularMoviesRecyclerView.setVisibility(statusViewState.isStatusSuccess())

    }
}
