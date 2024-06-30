package com.example.cleanmovieapp.common

object ThrowableReporter {
    private var report: String? = null
    fun setReport(throwable: Throwable){
        report = throwable.message.toString()
    }
    fun fetchReport():String?{
        return report?.let {
            it
        }
    }
}