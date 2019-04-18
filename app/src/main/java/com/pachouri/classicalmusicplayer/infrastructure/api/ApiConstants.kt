package com.pachouri.classicalmusicplayer.infrastructure.api

import com.pachouri.classicalmusicplayer.BuildConfig

/**
 * Created by Ankit Pachouri on 17/04/19.
 *
 * Keep all the constants related with the api calls here
 */
class ApiConstants {
    companion object {
        const val BASE_URL = BuildConfig.BASE_URL
        const val API_PATH = "api/"
        const val ENDPOINT_GET_TOKEN = API_PATH + "token"
    }
}