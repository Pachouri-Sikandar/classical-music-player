package com.pachouri.classicalmusicplayer.infrastructure.api.request.query

import java.util.*

/**
 * Created by Ankit Pachouri on 18/04/19.
 */
class ListQuery : BaseListQuery {

    private var mOffset: Int? = 0
    private var mLimit: Int? = 20

    override fun getQueryMap(): Map<String, String> {
        val map = HashMap<String, String>()
        map["offset"] = mOffset.toString()
        map["limit"] = mLimit.toString()
        return map
    }

    override fun getOffset(): Int? {
        return mOffset
    }

    override fun getLimit(): Int? {
        return mLimit
    }

    override fun setOffset(offset: Int): ListQuery {
        mOffset = offset
        return this
    }

    override fun setLimit(limit: Int): ListQuery {
        mLimit = limit
        return this
    }
}