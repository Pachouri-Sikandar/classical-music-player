package com.pachouri.classicalmusicplayer.infrastructure.api.provider.retrofit2

import android.content.Context
import com.pachouri.classicalmusicplayer.infrastructure.dao.SessionSharedPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * Created by Ankit Pachouri on 18/04/19.
 */
class AuthenticationInterceptor(context: Context) : Interceptor {

    private val mContext = context

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val builder = originalRequest.newBuilder()
        setHeaders(builder)
        val newRequest = builder.build()
        return chain.proceed(newRequest)
    }

    /**
     * Set headers in the request
     */
    private fun setHeaders(builder: Request.Builder) {
        var sharedPreferences = SessionSharedPreferences(mContext)
        var authorizationkey: String? = ""
        if (sharedPreferences.isRefreshToken()) {
            authorizationkey = sharedPreferences.getAuthorizationKey()
        } else {
            authorizationkey = sharedPreferences.getAccessToken()
        }
        builder.addHeader("Authorization", authorizationkey)
    }
}