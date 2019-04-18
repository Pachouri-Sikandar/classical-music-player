package com.pachouri.classicalmusicplayer.infrastructure.api.response.albums

import android.content.ClipData.Item
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ankit Pachouri on 18/04/19.
 */

class AlbumsBaseResponse {

    @SerializedName("href")
    @Expose
    private var href: String? = null
    @SerializedName("items")
    @Expose
    private var items: List<Item>? = null
    @SerializedName("limit")
    @Expose
    private var limit: Int? = null
    @SerializedName("next")
    @Expose
    private var next: String? = null
    @SerializedName("offset")
    @Expose
    private var offset: Int? = null
    @SerializedName("previous")
    @Expose
    private var previous: String? = null
    @SerializedName("total")
    @Expose
    private var total: Int? = null

    fun getHref(): String? {
        return href
    }

    fun getItems(): List<Item>? {
        return items
    }

    fun getLimit(): Int? {
        return limit
    }

    fun getNext(): String? {
        return next
    }

    fun getOffset(): Int? {
        return offset
    }

    fun getPrevious(): String? {
        return previous
    }

    fun getTotal(): Int? {
        return total
    }
}