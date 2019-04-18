package com.pachouri.classicalmusicplayer.infrastructure.api.provider.retrofit2

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import com.pachouri.classicalmusicplayer.infrastructure.api.AppApi
import com.pachouri.classicalmusicplayer.infrastructure.api.callback.ApiCallback
import com.pachouri.classicalmusicplayer.infrastructure.api.observer.RetrofitCallBackObserver
import com.pachouri.classicalmusicplayer.infrastructure.api.response.AuthorizationResponse
import com.pachouri.classicalmusicplayer.infrastructure.api.response.albums.AlbumsBaseResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ankit Pachouri on 17/04/19.
 *
 * This class will have all the api implementation methods
 */
class RetrofitApi(context: Context) : AppApi {

    private var mContext: Context
    private var mCompositeDisposable: CompositeDisposable

    init {
        mContext = context
        mCompositeDisposable = CompositeDisposable()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun dispose() {
        mCompositeDisposable.dispose()
    }

    override fun isDisposed(): Boolean {
        return mCompositeDisposable.isDisposed
    }

    /**
     * Api to get access token
     */
    override fun getAccessToken(callback: ApiCallback<AuthorizationResponse>): Disposable {
        val disposable = RetrofitSingleton().getInstance(mContext).getAccessToken("client_credentials")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(RetrofitCallBackObserver(callback))
        mCompositeDisposable.add(disposable)
        return disposable
    }

    /**
     * Api to get list of Albums available by only a single artist  (for the sake of demo): Artist id- 0oSGxfWSnnOXhD2fKuz2Gy
     */
    override fun getAlbumsList(callback: ApiCallback<AlbumsBaseResponse>): Disposable {
        val disposable = RetrofitSingleton().getInstance(mContext)
            .getAlbumsList("https://api.spotify.com/v1/artists/0oSGxfWSnnOXhD2fKuz2Gy/albums?offset=20&limit=10")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(RetrofitCallBackObserver(callback))
        mCompositeDisposable.add(disposable)
        return disposable
    }
}