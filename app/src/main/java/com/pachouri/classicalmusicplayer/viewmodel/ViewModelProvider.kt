package com.pachouri.classicalmusicplayer.viewmodel

/**
 * Created by Ankit Pachouri on 17/04/19.
 *
 * ViewModelProvider singleton
 * Class which holds all the view models definitions
 */
class ViewModelProvider {

    @Volatile
    private var mInstance: ViewModelProvider? = null

    @Synchronized
    fun getInstance(): ViewModelProvider? {
        if (mInstance == null) {
            synchronized(ViewModelProvider::class.java) {
                mInstance = ViewModelProvider()
            }

        }
        return mInstance
    }
}