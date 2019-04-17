package com.pachouri.classicalmusicplayer.infrastructure.api

import android.app.Application
import android.arch.lifecycle.LifecycleOwner
import com.pachouri.classicalmusicplayer.infrastructure.api.provider.retrofit2.RetrofitApi

/**
 * Created by Ankit Pachouri on 17/04/19.
 *
 *  Class to get the RetrofitApi instance,
 * The purpose of this class is - in future if we wish to change the network calls provider
 * we can simply change it from here, we do not need to change all the api
 * calls throughout the app.
 */
class Api {

    companion object {
        @Volatile
        private var instance: AppApi? = null

        fun getInstance(application: Application, lifecycleOwner: LifecycleOwner): AppApi =
            instance ?: synchronized(this) {
                val api = RetrofitApi(application)
                lifecycleOwner.lifecycle.addObserver(api)
                return api
            }
    }
}