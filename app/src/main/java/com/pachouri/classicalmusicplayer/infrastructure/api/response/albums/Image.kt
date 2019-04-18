package com.pachouri.classicalmusicplayer.infrastructure.api.response.albums

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ankit Pachouri on 18/04/19.
 */
class Image {

    @SerializedName("height")
    @Expose
    private var height: Int? = null
    @SerializedName("url")
    @Expose
    private var url: String? = null
    @SerializedName("width")
    @Expose
    private var width: Int? = null

    fun getHeight(): Int? {
        return height
    }

    fun getUrl(): String? {
        return url
    }

    fun getWidth(): Int? {
        return width
    }
}