package com.pachouri.classicalmusicplayer.util.imageloader

/**
 * Created by Ankit Pachouri on 17/04/19.
 *
 * ImageLoader class which returns the instance of GlideImageLoader.
 * Purpose of this class is in case if we wish to change the image loading library simply return the instance of the same
 */

class ImageLoader {

    companion object {
        @Volatile
        private var instance: AppImageLoader<AppImageView>? = null

        fun getInstance(): AppImageLoader<AppImageView> =
            instance ?: synchronized(this) {
                return GlideImageLoader()
            }
    }
}