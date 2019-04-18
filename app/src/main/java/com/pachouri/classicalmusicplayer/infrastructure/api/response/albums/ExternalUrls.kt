package com.pachouri.classicalmusicplayer.infrastructure.api.response.albums

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ankit Pachouri on 18/04/19.
 */
class ExternalUrls {

    @SerializedName("spotify")
    @Expose
    private var spotify: String? = null

    fun getSpotify(): String? {
        return spotify
    }
}