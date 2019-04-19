package com.pachouri.classicalmusicplayer.viewmodel.provider

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.firstkotlin.viewmodel.LoadingState
import com.example.firstkotlin.viewmodel.MutableLiveList
import com.pachouri.classicalmusicplayer.infrastructure.api.callback.ApiCallback
import com.pachouri.classicalmusicplayer.infrastructure.api.request.query.BaseListQuery
import com.pachouri.classicalmusicplayer.infrastructure.api.response.albums.AlbumsBaseResponse
import com.pachouri.classicalmusicplayer.viewmodel.BaseListViewModelContract

/**
 * Created by Ankit Pachouri on 18/04/19.
 *
 * This View Model should be reusable for all the types of list
 */

abstract class BaseListViewModel<T, A, Q : BaseListQuery>(application: Application) : AppViewModel(application),
    BaseListViewModelContract<A> {

    /**
     * List wrapped in a MutableLiveList.
     */
    private val mList: MutableLiveList<A>

    /**
     * Load State for the initial load
     */
    private val mInitialLoadState: MutableLiveData<LoadingState>

    /**
     * Load state for on scroll (load more)
     */
    private val mLoadMoreLoadState: MutableLiveData<LoadingState>

    /**
     * Does this list have more items
     */
    private val mCanLoadMoreItems: MutableLiveData<Boolean>

    /**
     * Abstract method to make the api call
     */
    protected abstract fun makeListApiCall(state: MutableLiveData<LoadingState>, callback: ApiCallback<T>)

    /**
     * Get the query
     */
    protected abstract fun getQuery(): Q

    /**
     * Initialise the list
     */
    protected abstract fun initialiseList(): MutableLiveList<A>

    init {
        mList = initialiseList()
        mInitialLoadState = MutableLiveData()
        mLoadMoreLoadState = MutableLiveData()
        mCanLoadMoreItems = MutableLiveData()
        mCanLoadMoreItems.value = true
    }

    override fun getInitialLoadState(): LiveData<LoadingState> {
        return mInitialLoadState
    }

    override fun getLoadMoreLoadState(): LiveData<LoadingState> {
        return mLoadMoreLoadState
    }

    override fun getList(): LiveData<List<A>> {
        return mList
    }

    override fun getCanLoadMoreItems(): LiveData<Boolean> {
        return mCanLoadMoreItems
    }

    override fun loadMore(): Boolean {
        if (null != mCanLoadMoreItems.value) {
            return loadList(mLoadMoreLoadState)
        }
        return false
    }

    override fun refresh(): Boolean {
        if (mInitialLoadState.value != LoadingState.LOADING_COMPLETED) {
            return initialRefresh()
        }
        return false
    }

    /**
     * Handles the initial refresh. Should only be called when the first page load hasn't been called successfully
     */
    private fun initialRefresh(): Boolean {
        return loadList(mInitialLoadState)
    }

    private fun loadList(state: MutableLiveData<LoadingState>): Boolean {
        //Make sure nothing is loading.
        if (mLoadMoreLoadState.value !== LoadingState.LOADING && mInitialLoadState.value !== LoadingState.LOADING) {
            Log.v("LIST_LOAD OFFSET", ": " + mList.size())
            getQuery().setOffset(mList.size())
            //set the current state to load to prevent a lock.
            state.setValue(LoadingState.LOADING)
            makeListApiCall(state, object : ApiCallback<T>() {
                override fun onSuccess(response: T) {
                    var baseResponse: AlbumsBaseResponse = response as AlbumsBaseResponse
                    var listt: List<A> = baseResponse.getItems() as List<A>
                    mList.addAll(listt)
                    Log.v("LIST_LOAD", "ITEMS SIZE: " + listt.size)
                    state.setValue(LoadingState.LOADING_COMPLETED)
                    val limit = getQuery().getLimit() ?: 0
                    val noMoreItems = listt.size >= limit
                    if (noMoreItems != mCanLoadMoreItems.value) {
                        mCanLoadMoreItems.setValue(noMoreItems)
                    }
                }

                override fun onError(errorMesage: String?) {
                    state.setValue(LoadingState.LOADING_FAILED.withError(errorMesage ?: ""))
                }
            })
            return true
        } else {//If loading doesn't happen cause of other loading, mark the state
            return state.value === LoadingState.LOADING
        }
    }
}