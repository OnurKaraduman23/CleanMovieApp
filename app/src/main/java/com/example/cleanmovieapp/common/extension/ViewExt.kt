package com.example.cleanmovieapp.common.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

fun <T : ViewBinding> ViewGroup.inflateCustomView(
    viewBindingFactory: (LayoutInflater, ViewGroup, Boolean) -> T
): T {
    return inflate(viewBindingFactory, attachToParent = true)
}

fun <T : ViewBinding> ViewGroup.inflate(
    viewBindingFactory: (LayoutInflater, ViewGroup, Boolean) -> T,
    attachToParent: Boolean = false,
): T {
    return viewBindingFactory.invoke(
        LayoutInflater.from(context),
        this,
        attachToParent
    )
}

fun View.setVisibility(isVisible: Boolean?) {
    visibility = if (isVisible == true) View.VISIBLE else View.GONE
}