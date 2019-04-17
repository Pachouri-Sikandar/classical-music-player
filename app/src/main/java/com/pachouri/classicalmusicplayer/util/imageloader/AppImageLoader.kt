package com.pachouri.classicalmusicplayer.util.imageloader

import android.support.annotation.DrawableRes
import android.widget.ImageView

/**
 * Created by Ankit Pachouri on 17/04/19.
 *
 * Imageloading methods with different parameters
 */
interface AppImageLoader<I : ImageView> {

    //Load image with url, place holder drawable and error drawable
    fun load(imageView: I, url: String, @DrawableRes placeholderResId: Int, @DrawableRes errorResId: Int)

    //Load image with url without any placeholder
    fun load(imageView: I, url: String)

    //Load image with drawable
    fun load(imageView: I, @DrawableRes resourceId: Int)
}