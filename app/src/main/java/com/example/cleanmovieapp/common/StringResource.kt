package com.example.cleanmovieapp.common

import android.content.Context


object StringResource {

    private lateinit var applicationContext: Context

    fun init(context: Context) {
        applicationContext = context.applicationContext
    }

    fun getString(resId: Int): String {
        return applicationContext.getString(resId)
    }

}