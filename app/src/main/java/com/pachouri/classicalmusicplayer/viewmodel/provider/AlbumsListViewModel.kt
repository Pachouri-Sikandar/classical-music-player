package com.pachouri.classicalmusicplayer.viewmodel.provider

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.example.firstkotlin.viewmodel.LoadingState
import com.example.firstkotlin.viewmodel.MutableLiveList
import com.pachouri.classicalmusicplayer.infrastructure.api.Api
import com.pachouri.classicalmusicplayer.infrastructure.api.AppApi
import com.pachouri.classicalmusicplayer.infrastructure.api.callback.ApiCallback
import com.pachouri.classicalmusicplayer.infrastructure.api.request.query.ListQuery
import com.pachouri.classicalmusicplayer.infrastructure.api.response.albums.AlbumsBaseResponse
import com.pachouri.classicalmusicplayer.infrastructure.api.response.albums.Item

/**
 * Created by Ankit Pachouri on 19/04/19.
 */

class AlbumsListViewModel(application: Application) :
    BaseListViewModel<AlbumsBaseResponse, Item, ListQuery>(application) {

    private var mQuery: ListQuery = ListQuery()
    private val mList: MutableLiveList<Item>? = null
    private var mApi: AppApi = Api.getInstance(application, this);

    override fun makeListApiCall(state: MutableLiveData<LoadingState>, callback: ApiCallback<AlbumsBaseResponse>) {
        mApi.getAlbumsList(callback)
    }

    override fun getQuery(): ListQuery {
        return mQuery
    }

    override fun initialiseList(): MutableLiveList<Item> {
        if (null == mList) {
            return MutableLiveList()
        }
        return mList
    }
}