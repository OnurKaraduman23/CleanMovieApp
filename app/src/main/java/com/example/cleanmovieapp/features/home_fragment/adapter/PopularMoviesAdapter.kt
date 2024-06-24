package com.example.cleanmovieapp.features.home_fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanmovieapp.common.Constants.IMAGES_BASE_URL
import com.example.cleanmovieapp.common.Constants.IMAGE_400
import com.example.cleanmovieapp.common.extension.loadImageView
import com.example.cleanmovieapp.databinding.ViewMoviesCardBinding
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovie

class PopularMoviesAdapter() :
    PagingDataAdapter<PopularMovie, PopularMoviesAdapter.PopularMoviesViewHolder>(
        MovieItemDiffCallback()
    ) {


    inner class PopularMoviesViewHolder(private val binding: ViewMoviesCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: PopularMovie) {
            binding.apply {
                textViewTitle.text = result.title
                textViewImdb.text = result.voteAverage.toString()
                trendingImageView.loadImageView(IMAGES_BASE_URL + IMAGE_400 + result.posterPath)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        val binding =
            ViewMoviesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }
}

class MovieItemDiffCallback : DiffUtil.ItemCallback<PopularMovie>() {
    override fun areItemsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean {
        return oldItem == newItem
    }
}
