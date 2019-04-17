package com.pachouri.classicalmusicplayer.infrastructure.api.callback

/**
 * Created by Ankit Pachouri on 17/04/19.
 *
 * Callback for the Api calls
 */
abstract class ApiCallback<T> {

    abstract fun onSuccess(response: T)

    abstract fun onError(errorMesage: String? = null)

    fun onStart() {}

    fun onComplete() {}
}