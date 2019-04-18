package com.pachouri.classicalmusicplayer.infrastructure.api.response.albums

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ankit Pachouri on 18/04/19.
 */
class Artist {

    @SerializedName("external_urls")
    @Expose
    private var externalUrls: ExternalUrls? = null
    @SerializedName("href")
    @Expose
    private var href: String? = null
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("type")
    @Expose
    private var type: String? = null
    @SerializedName("uri")
    @Expose
    private var uri: String? = null

    fun getExternalUrls(): ExternalUrls? {
        return externalUrls
    }

    fun getHref(): String? {
        return href
    }

    fun getId(): String? {
        return id
    }

    fun getName(): String? {
        return name
    }

    fun getType(): String? {
        return type
    }

    fun getUri(): String? {
        return uri
    }
}