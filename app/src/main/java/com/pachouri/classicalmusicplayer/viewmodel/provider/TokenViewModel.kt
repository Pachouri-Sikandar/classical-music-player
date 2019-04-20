package com.pachouri.classicalmusicplayer.viewmodel.provider

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.example.firstkotlin.viewmodel.LoadingState
import com.pachouri.classicalmusicplayer.infrastructure.api.Api
import com.pachouri.classicalmusicplayer.infrastructure.api.AppApi
import com.pachouri.classicalmusicplayer.infrastructure.api.callback.ApiCallback
import com.pachouri.classicalmusicplayer.infrastructure.api.response.AuthorizationResponse

/**
 * Created by Ankit Pachouri on 20/04/19.
 */
class TokenViewModel(application: Application) : AppViewModel(application) {

    private val mLoadingState: MutableLiveData<LoadingState> = MutableLiveData()
    private val mApi: AppApi = Api.getInstance(application, this)
    private val mAuthorization: MutableLiveData<AuthorizationResponse> = MutableLiveData()

    /**
     * Get the loading state
     */
    fun getLoadingState(): MutableLiveData<LoadingState> {
        return mLoadingState
    }

    /**
     * Get the authorization object
     */
    fun getAuthorizationObject(): MutableLiveData<AuthorizationResponse> {
        return mAuthorization
    }

    /**
     * Api call to get the access token
     */
    fun getAccessToken() {
        mLoadingState.value = LoadingState.LOADING
        mApi.getAccessToken(object : ApiCallback<AuthorizationResponse>() {
            override fun onSuccess(response: AuthorizationResponse) {
                mLoadingState.value = LoadingState.LOADING_COMPLETED
                mAuthorization.value = response
            }

            override fun onError(errorMesage: String?) {
                mLoadingState.value = LoadingState.LOADING_FAILED
            }
        })
    }
}