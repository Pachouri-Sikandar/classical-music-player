package com.pachouri.classicalmusicplayer.infrastructure.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ankit Pachouri on 18/04/19.
 */
class AuthorizationResponse {

    @SerializedName("access_token")
    @Expose
    private val accessToken: String? = null
    @SerializedName("token_type")
    @Expose
    private val tokenType: String? = null
    @SerializedName("expires_in")
    @Expose
    private val expiresIn: Long = 0
}