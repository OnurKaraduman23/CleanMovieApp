package com.example.cleanmovieapp

import android.app.Application
import com.example.cleanmovieapp.common.StringResource
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CleanMovieApp : Application(){
    override fun onCreate() {
        super.onCreate()
        StringResource.init(this)
    }
}