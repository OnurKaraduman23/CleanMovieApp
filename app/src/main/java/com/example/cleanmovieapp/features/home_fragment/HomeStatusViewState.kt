package com.example.cleanmovieapp.features.home_fragment


import com.example.cleanmovieapp.R
import com.example.cleanmovieapp.common.StringResource
import com.example.cleanmovieapp.common.Status
import com.example.cleanmovieapp.common.ThrowableReporter

data class HomeStatusViewState(
    private val status: Status
) {

    fun getTitle(): String {
        return if (status == Status.ERROR) {
            StringResource.getString(R.string.error_occurred)
        } else {
            " "
        }
    }

    fun getDescription(): String {
        return if (status == Status.ERROR) {
            "${StringResource.getString(R.string.error_message)}: " + "${ThrowableReporter.fetchReport()}" // Buranın okunabilirliği biraz düştü
        } else {
            " "
        }
    }

    fun getButtonText(): String {
        return if (status == Status.ERROR) {
            StringResource.getString(R.string.try_again)
        } else {
            " "
        }
    }

    fun isStatusError(): Boolean {
        return status == Status.ERROR
    }

    fun isStatusLoading(): Boolean {
        return status == Status.LOADING
    }

    fun isStatusSuccess(): Boolean {
        return status == Status.SUCCESS
    }

    fun isStatusViewVisible(): Boolean {
        return isStatusSuccess().not()
    }


}