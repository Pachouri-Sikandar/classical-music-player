package com.pachouri.classicalmusicplayer.util.imageloader

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Ankit Pachouri on 17/04/19.
 *
 * Glide image loading library which implements AppImageLoader to define different ways (methods) to load image
 *
 */
class GlideImageLoader : AppImageLoader<AppImageView> {

    companion object {
        private const val TIME_OUT = 10000
    }

    /**
     * Load image with url, place holder drawable and error drawable
     */
    override fun load(imageView: AppImageView, url: String, placeholderResId: Int, errorResId: Int) {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.placeholder(placeholderResId).error(errorResId).timeout(TIME_OUT)
        Glide.with(imageView).setDefaultRequestOptions(requestOptions).load(url).into(imageView)
    }

    /**
     * Load image with url without any placeholder
     */
    override fun load(imageView: AppImageView, url: String) {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.timeout(TIME_OUT)
        Glide.with(imageView).setDefaultRequestOptions(requestOptions).load(url).into(imageView)
    }

    /**
     * Load image with drawable
     */
    override fun load(imageView: AppImageView, resourceId: Int) {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.timeout(TIME_OUT)
        Glide.with(imageView).setDefaultRequestOptions(requestOptions).load(resourceId).into(imageView)
    }
}