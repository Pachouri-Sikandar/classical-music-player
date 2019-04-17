package com.pachouri.classicalmusicplayer.infrastructure.api.provider.retrofit2

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import com.pachouri.classicalmusicplayer.infrastructure.api.AppApi
import io.reactivex.disposables.CompositeDisposable

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
}