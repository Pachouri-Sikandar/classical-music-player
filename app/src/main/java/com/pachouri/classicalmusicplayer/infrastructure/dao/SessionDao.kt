package com.pachouri.classicalmusicplayer.infrastructure.dao

/**
 * Created by Ankit Pachouri on 18/04/19.
 */
interface SessionDao {

    /**
     * Save the Base64 (Authorization) key converted using client id and secret key
     */
    fun saveAuthorizationKey(key: String)

    /**
     * Get the Base64 (Authorization) key
     */
    fun getAuthorizationKey(): String

    /**
     * Save Access Token
     */
    fun saveAccessToken(token: String)

    /**
     * Get the Access Token
     */
    fun getAccessToken(): String

    /**
     * Save Authorization type whether its the initial base64 key or the access token
     */
    fun saveAuthorizationType(isRefreshToken: Boolean)

    /**
     * Get if its refresh token
     */
    fun isRefreshToken(): Boolean
}