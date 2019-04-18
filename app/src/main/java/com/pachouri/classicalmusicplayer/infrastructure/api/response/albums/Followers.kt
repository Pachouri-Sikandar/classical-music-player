package com.pachouri.classicalmusicplayer.infrastructure.api.response.albums

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ankit Pachouri on 18/04/19.
 */
class Followers {

    @SerializedName("href")
    @Expose
    private var href: Any? = null
    @SerializedName("total")
    @Expose
    private var total: Int? = null

    fun getHref(): Any? {
        return href
    }

    fun getTotal(): Int? {
        return total
    }
}