package com.pachouri.classicalmusicplayer.infrastructure.api

import android.arch.lifecycle.LifecycleObserver
import com.pachouri.classicalmusicplayer.infrastructure.api.callback.ApiCallback
import com.pachouri.classicalmusicplayer.infrastructure.api.response.AuthorizationResponse
import io.reactivex.disposables.Disposable

/**
 * Created by Ankit Pachouri on 17/04/19.
 */
interface AppApi : LifecycleObserver, Disposable {

    /**
     * Api to get access token
     */
    fun getAccessToken(callback: ApiCallback<AuthorizationResponse>): Disposable
}