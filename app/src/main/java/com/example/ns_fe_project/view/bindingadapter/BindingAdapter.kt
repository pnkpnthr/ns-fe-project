package com.example.ns_fe_project.view.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("loadingVisibility")
fun View.setLoadingVisibility(isLoading: Boolean) {
    visibility = if (isLoading) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("errorVisibility")
fun View.setErrorVisibility(isError: Boolean) {
    visibility = if (isError) {
        View.VISIBLE
    } else {
        View.GONE
    }
}