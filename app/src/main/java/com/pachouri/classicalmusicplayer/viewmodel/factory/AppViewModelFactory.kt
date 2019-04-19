package com.pachouri.classicalmusicplayer.viewmodel.factory

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
import com.pachouri.classicalmusicplayer.viewmodel.provider.AlbumsListViewModel

/**
 * Created by Ankit Pachouri on 18/04/19.
 *
 * ViewModelFactory to get the type of ViewModel
 */

class AppViewModelFactory(fragment: Fragment) : ViewModelProvider.NewInstanceFactory() {

    private var mApplication: Application

    init {
        mApplication = fragment.activity!!.application
    }

    companion object {
        fun getInstance(fragment: Fragment): AppViewModelFactory {
            return AppViewModelFactory(fragment)
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumsListViewModel::class.java)) {
            return AlbumsListViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}