package com.pachouri.classicalmusicplayer.viewmodel

import android.arch.lifecycle.LiveData
import com.example.firstkotlin.viewmodel.LoadingState

/**
 * Created by Ankit Pachouri on 18/04/19.
 */
interface BaseListViewModelContract<T> {

    /**
     * Get the load state for the initial page load.
     */
    fun getInitialLoadState(): LiveData<LoadingState>

    /**
     * Get an observable wrapper for the load state for load more.
     */
    fun getLoadMoreLoadState(): LiveData<LoadingState>

    /**
     * Get an observable wrapper for the list of objects currently loaded.
     */
    fun getList(): LiveData<List<T>>

    /**
     * Get an observable wrapper for has more
     */
    fun getCanLoadMoreItems(): LiveData<Boolean>

    /**
     * Triggers a load more - call this when the user scrolls down.
     */
    fun loadMore(): Boolean

    /**
     * Triggers loading new items when the page first loads.
     */
    fun refresh(): Boolean
}