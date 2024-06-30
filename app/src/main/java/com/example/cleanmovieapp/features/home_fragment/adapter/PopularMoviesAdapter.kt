package com.example.cleanmovieapp.features.home_fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanmovieapp.common.Constants.IMAGES_BASE_URL
import com.example.cleanmovieapp.common.Constants.IMAGE_400
import com.example.cleanmovieapp.common.extension.loadImageView
import com.example.cleanmovieapp.databinding.ViewMoviesCardBinding
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovie

class PopularMoviesAdapter :
    ListAdapter<PopularMovie, PopularMoviesAdapter.NewMoviesViewHolder>(PopularMovieDiffCallback()) {

    class NewMoviesViewHolder(private val binding: ViewMoviesCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(popularMovie: PopularMovie) {
            binding.apply {
                textViewTitle.text = popularMovie.title
                trendingImageView.loadImageView(IMAGES_BASE_URL + IMAGE_400 + popularMovie.posterPath)
                textViewImdb.text = popularMovie.voteAverage.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewMoviesViewHolder {
        val binding =
            ViewMoviesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewMoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PopularMovieDiffCallback : DiffUtil.ItemCallback<PopularMovie>() {
        override fun areItemsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean {
            return oldItem == newItem
        }
    }
}
