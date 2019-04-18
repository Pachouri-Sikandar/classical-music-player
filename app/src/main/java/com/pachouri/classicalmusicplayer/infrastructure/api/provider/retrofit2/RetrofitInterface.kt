package com.pachouri.classicalmusicplayer.infrastructure.api.provider.retrofit2

import com.pachouri.classicalmusicplayer.infrastructure.api.ApiConstants
import com.pachouri.classicalmusicplayer.infrastructure.api.response.AuthorizationResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Ankit Pachouri on 17/04/19.
 */
interface RetrofitInterface {

    @FormUrlEncoded
    @POST(ApiConstants.ENDPOINT_GET_TOKEN)
    fun getAccessToken(@Field("grant_type") value: String): Observable<AuthorizationResponse>
}