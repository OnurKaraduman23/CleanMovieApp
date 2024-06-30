package com.example.cleanmovieapp.features.home_fragment

import com.example.cleanmovieapp.common.Status

data class HomeStatusViewState(private val status: Status) {
    fun getTitle(): String {
        return if (status == Status.ERROR) {
            "Error occured"
        } else {
            " "
        }
    }

    fun getDescription(): String {
        return if (status == Status.ERROR) {
            "Error occured Description"
        } else {
            " "
        }
    }

    fun getButtonText(): String {
        return if (status == Status.ERROR) {
            "Try Again"
        } else {
            " "
        }
    }

    fun isStatusError(): Boolean {
        return  status == Status.ERROR
    }

    fun isStatusLoading(): Boolean{
        return status == Status.LOADING
    }

    fun isStatusSuccess(): Boolean {
        return  status == Status.SUCCESS
    }
    fun isStatusViewVisible(): Boolean {
        return  isStatusSuccess().not()
    }


}