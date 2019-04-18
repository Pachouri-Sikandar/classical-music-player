package com.pachouri.classicalmusicplayer.viewmodel.factory

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment

/**
 * Created by Ankit Pachouri on 18/04/19.
 *
 * ViewModelFactory to get the type of ViewModel
 */

class AppViewModelFactory(fragment: Fragment) : ViewModelProvider.NewInstanceFactory() {

    private var mApplication: Application?

    init {
        mApplication = fragment.activity?.application
    }

    companion object {
        fun getInstance(fragment: Fragment): AppViewModelFactory {
            return AppViewModelFactory(fragment)
        }
    }
}