package com.example.firstkotlin.viewmodel

/**
 * Created by Ankit Pachouri on 18/04/19.
 *
 * Loading States
 */
enum class LoadingState {
    LOADING,
    LOADING_COMPLETED,
    LOADING_FAILED;

    private var mError: String? = null

    fun withError(error: String): LoadingState {
        mError = error
        return this
    }
}