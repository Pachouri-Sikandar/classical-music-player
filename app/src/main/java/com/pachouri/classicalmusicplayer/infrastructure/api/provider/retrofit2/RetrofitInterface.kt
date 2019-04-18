package com.pachouri.classicalmusicplayer.infrastructure.api.provider.retrofit2

import com.pachouri.classicalmusicplayer.infrastructure.api.ApiConstants
import com.pachouri.classicalmusicplayer.infrastructure.api.response.AuthorizationResponse
import com.pachouri.classicalmusicplayer.infrastructure.api.response.albums.AlbumsBaseResponse
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by Ankit Pachouri on 17/04/19.
 */
interface RetrofitInterface {

    @FormUrlEncoded
    @POST(ApiConstants.ENDPOINT_GET_TOKEN)
    fun getAccessToken(@Field("grant_type") value: String): Observable<AuthorizationResponse>

    @GET //Using @Url here - so that retrofit will the given url as the base url
    fun getAlbumsList(@Url url: String): Observable<AlbumsBaseResponse>
}