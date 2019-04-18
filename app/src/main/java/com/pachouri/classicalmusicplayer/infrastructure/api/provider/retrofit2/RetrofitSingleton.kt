package com.pachouri.classicalmusicplayer.infrastructure.api.provider.retrofit2

import android.content.Context
import com.google.gson.GsonBuilder
import com.pachouri.classicalmusicplayer.infrastructure.api.ApiConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier
import java.util.concurrent.TimeUnit


/**
 * Created by Ankit Pachouri on 17/04/19.
 */
class RetrofitSingleton {

    @Volatile
    private var instace: RetrofitInterface? = null

    fun getInstance(context: Context): RetrofitInterface = instace ?: synchronized(this) {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val gsonBuilder = GsonBuilder()
        gsonBuilder.excludeFieldsWithModifiers(
            Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC
        )
        gsonBuilder.excludeFieldsWithoutExposeAnnotation()
        val gson = gsonBuilder.create()

        val okHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(AuthenticationInterceptor(context))
            .addInterceptor(logging)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(RetrofitInterface::class.java)
    }
}