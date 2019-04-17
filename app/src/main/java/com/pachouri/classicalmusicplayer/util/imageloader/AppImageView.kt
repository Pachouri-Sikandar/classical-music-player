package com.pachouri.classicalmusicplayer.util.imageloader

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

/**
 * Created by Ankit Pachouri on 17/04/19.
 *
 * Purpose of this image view:
 * To use any image view type for Image Loading simply change the parent class
 * no need to make changes in xml files
 */
class AppImageView : AppCompatImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
}