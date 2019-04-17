package com.pachouri.classicalmusicplayer.infrastructure.api.observer

import com.pachouri.classicalmusicplayer.infrastructure.api.callback.ApiCallback
import io.reactivex.observers.DisposableObserver

/**
 * Created by Ankit Pachouri on 17/04/19.
 *
 * Observer for the api callback
 */

class RetrofitCallBackObserver<T>(apiCallback: ApiCallback<T>) : DisposableObserver<T>() {

    private var mApiCallBack: ApiCallback<T>? = null

    init {
        mApiCallBack = apiCallback
    }

    override fun onComplete() {
        mApiCallBack?.onComplete()
    }

    override fun onNext(t: T) {
        if (t != null) {
            mApiCallBack?.onSuccess(t)
        } else {
            mApiCallBack?.onError("Unknown Error")
        }
    }

    override fun onError(e: Throwable) {
        mApiCallBack?.onError(e.message)
    }

    override fun onStart() {
        super.onStart()
        mApiCallBack?.onStart()
    }
}