package com.pachouri.classicalmusicplayer.viewmodel

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.pachouri.classicalmusicplayer.viewmodel.factory.AppViewModelFactory
import com.pachouri.classicalmusicplayer.viewmodel.provider.AlbumsListViewModel
import com.pachouri.classicalmusicplayer.viewmodel.provider.TokenViewModel

/**
 * Created by Ankit Pachouri on 17/04/19.
 *
 * ViewModelProvider singleton
 * Class which holds all the view models definitions
 */
class ViewModelProvider {

    companion object {
        @Volatile
        private var mInstance: ViewModelProvider? = null

        fun getInstance(): ViewModelProvider =
            mInstance ?: synchronized(this) {
                return ViewModelProvider()
            }
    }


    fun getAlbumsListViewModel(fragment: Fragment): AlbumsListViewModel {
        val factory = AppViewModelFactory.getInstance(fragment)
        return ViewModelProviders.of(fragment, factory)
            .get(AlbumsListViewModel::class.java.getName(), AlbumsListViewModel::class.java)
    }

    fun getTokenViewModel(fragment: Fragment): TokenViewModel {
        val factory = AppViewModelFactory.getInstance(fragment)
        return ViewModelProviders.of(fragment, factory)
            .get(TokenViewModel::class.java.getName(), TokenViewModel::class.java)
    }
}