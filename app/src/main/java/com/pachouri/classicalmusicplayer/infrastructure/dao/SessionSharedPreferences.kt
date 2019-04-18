package com.pachouri.classicalmusicplayer.infrastructure.dao

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Ankit Pachouri on 18/04/19.
 */
class SessionSharedPreferences(context: Context) : SessionDao {

    private val KEY_USER_SESSION = "UserSessionPreferences"
    private val KEY_AUTHORIZATION = "Authorization"
    private val KEY_ACCESS_TOKEN = "AccessToken"
    private val KEY_AUTHORIZATION_TYPE = "AuthorizationType"

    private val mContext: Context = context

    private fun getSessionSharedPreferences(): SharedPreferences {
        return mContext.getSharedPreferences(KEY_USER_SESSION, Context.MODE_PRIVATE)
    }

    override fun saveAuthorizationKey(key: String) {
        val sharedPreferences = getSessionSharedPreferences()
        val editor = sharedPreferences.edit()
        editor.putString(KEY_AUTHORIZATION, key)
        editor.commit()
    }

    override fun getAuthorizationKey(): String {
        val preferences = getSessionSharedPreferences()
        return preferences.getString(KEY_AUTHORIZATION, "")
    }

    override fun saveAccessToken(token: String) {
        val sharedPreferences = getSessionSharedPreferences()
        val editor = sharedPreferences.edit()
        editor.putString(KEY_ACCESS_TOKEN, token)
        editor.commit()
    }

    override fun getAccessToken(): String {
        val preferences = getSessionSharedPreferences()
        return preferences.getString(KEY_ACCESS_TOKEN, "")
    }

    override fun saveAuthorizationType(isRefreshToken: Boolean) {
        val sharedPreferences = getSessionSharedPreferences()
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_AUTHORIZATION_TYPE, isRefreshToken)
        editor.commit()
    }

    override fun isRefreshToken(): Boolean {
        val preferences = getSessionSharedPreferences()
        return preferences.getBoolean(KEY_AUTHORIZATION_TYPE, false)
    }
}