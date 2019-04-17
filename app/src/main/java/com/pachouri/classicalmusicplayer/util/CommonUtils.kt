package com.pachouri.classicalmusicplayer.util

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

/**
 * Created by Ankit Pachouri on 17/04/19.
 *
 * Add the utility or common methods, variables etc. in this class to be used throughout the app
 */
class CommonUtils {

    companion object {

        /**
         * Method to show toast
         */
        fun showToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        /**
         * Method to check internet connection
         */
        fun isInternetConnected(context: Context): Boolean {
            var isInternetConnected = false
            try {
                val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                if (connManager.activeNetworkInfo.isConnected) {
                    isInternetConnected = true
                }
            } catch (ex: Exception) {
                isInternetConnected = false
            }
            return isInternetConnected
        }
    }
}