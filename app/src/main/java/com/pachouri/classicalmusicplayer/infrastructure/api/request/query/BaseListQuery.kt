package com.pachouri.classicalmusicplayer.infrastructure.api.request.query

/**
 * Created by Ankit Pachouri on 18/04/19.
 */

interface BaseListQuery {

    /**
     * Get the query map to be passed with the api
     */
    fun getQueryMap(): Map<String, String>

    /**
     * Get offset
     */
    fun getOffset(): Int?

    /**
     * Get limit
     */
    fun getLimit(): Int?

    /**
     * Set offset
     */
    fun setOffset(offset: Int): ListQuery

    /**
     * Set limit
     */
    fun setLimit(limit: Int): ListQuery
}