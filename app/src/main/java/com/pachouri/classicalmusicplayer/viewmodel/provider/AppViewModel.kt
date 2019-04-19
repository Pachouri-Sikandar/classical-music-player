package com.pachouri.classicalmusicplayer.viewmodel.provider

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.support.annotation.CallSuper
import android.support.annotation.NonNull

/**
 * Created by Ankit Pachouri on 18/04/19.
 *
 *  The purpose of this class is to use the View Model lifecycle owner
 */
open class AppViewModel(application: Application) : AndroidViewModel(application), LifecycleOwner {

    private val mRegister: LifecycleRegistry

    init {
        mRegister = LifecycleRegistry(this)
        mRegister.markState(Lifecycle.State.INITIALIZED)
        mRegister.markState(Lifecycle.State.CREATED)
        mRegister.markState(Lifecycle.State.STARTED)
    }

    @CallSuper
    public override fun onCleared() {
        mRegister.markState(Lifecycle.State.DESTROYED)
    }

    @NonNull
    override fun getLifecycle(): Lifecycle {
        return mRegister
    }
}