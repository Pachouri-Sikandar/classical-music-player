package com.pachouri.classicalmusicplayer.util

import android.content.Context
import android.net.ConnectivityManager
import android.util.Base64
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

        /**
         * Converts given string to base64 and adds required prefix to it
         */
        fun base64Conversion(conversionString: String, prefix: String): String {
            var bytes = ByteArray(0)
            try {
                bytes = conversionString.toByteArray(Charsets.UTF_8)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            val encoded = Base64.encodeToString(bytes, Base64.NO_WRAP)
            return prefix + " " + encoded
        }

        /**
         * Method to convert dp to pixel
         */
        fun convertDpToPixel(context: Context, dp: Float): Int {
            val density = context.resources.displayMetrics.density
            return (dp * density).toInt()
        }
    }
}