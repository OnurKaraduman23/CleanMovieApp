package com.example.cleanmovieapp.common.extension


import android.widget.ImageView
import coil.load
import com.example.cleanmovieapp.R

fun ImageView.loadImageView(imageUrl: String?) {
    this.load(imageUrl) {
        crossfade(600)
        error(R.drawable.ic_placeholder)
    }
}
