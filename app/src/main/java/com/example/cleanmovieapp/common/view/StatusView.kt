package com.example.cleanmovieapp.common.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.cleanmovieapp.common.extension.inflateCustomView
import com.example.cleanmovieapp.common.extension.setVisibility
import com.example.cleanmovieapp.databinding.ViewStatusBinding
import com.example.cleanmovieapp.features.home_fragment.HomeStatusViewState

class StatusView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = inflateCustomView(ViewStatusBinding::inflate)

    private var onErrorClicked: (() -> Unit) = {}
    init {
        binding.buttonStatus.setOnClickListener {
            onErrorClicked.invoke()
        }
    }

    fun setErrorClickListener(listener: (() -> Unit)){
        onErrorClicked = listener
    }
    fun setViewState(statusViewState: HomeStatusViewState) {
        binding.textViewStatusTitle.apply {
            text = statusViewState.getTitle()
        }
        binding.textViewStatusDescription.apply {
            text = statusViewState.getDescription()
        }
        binding.buttonStatus.apply {
            text = statusViewState.getButtonText()
        }
        binding.progressBarStatus.setVisibility(statusViewState.isStatusLoading())
        binding.linearLayoutStatusError.setVisibility(statusViewState.isStatusError())
        binding.root.setVisibility(statusViewState.isStatusViewVisible())
    }
}