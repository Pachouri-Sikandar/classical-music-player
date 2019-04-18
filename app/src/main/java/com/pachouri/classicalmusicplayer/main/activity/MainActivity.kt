package com.pachouri.classicalmusicplayer.main.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.pachouri.classicalmusicplayer.R
import com.pachouri.classicalmusicplayer.infrastructure.api.Api
import com.pachouri.classicalmusicplayer.infrastructure.api.callback.ApiCallback
import com.pachouri.classicalmusicplayer.infrastructure.api.response.AuthorizationResponse
import com.pachouri.classicalmusicplayer.infrastructure.dao.SessionSharedPreferences
import com.pachouri.classicalmusicplayer.util.AppConstants
import com.pachouri.classicalmusicplayer.util.CommonUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        checkAccessToken()
    }

    private fun checkAccessToken() {
        val sessionPreferences = SessionSharedPreferences(this)
        if (TextUtils.isEmpty(sessionPreferences.getAuthorizationKey())) {
            val conversionString = AppConstants.APP_CLIENT_ID + ":" + AppConstants.APP_SECRET_KEY
            val authorizationKey = CommonUtils.base64Conversion(conversionString, "Basic")
            sessionPreferences.saveAuthorizationKey(authorizationKey)
            sessionPreferences.saveAuthorizationType(true)
            Api.getInstance(application, this)
                .getAccessToken(object : ApiCallback<AuthorizationResponse>() {
                    override fun onSuccess(response: AuthorizationResponse) {

                    }

                    override fun onError(errorMesage: String?) {

                    }
                })
        } else {
            sessionPreferences.saveAuthorizationType(false)
        }
    }
}
