package com.example.firstkotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import java.util.*

/**
 * Created by Ankit Pachouri on 18/04/19.
 *
 * Extends Live Data class to hold a live list data
 */

class MutableLiveList<T> : MutableLiveData<List<T>>() {

    /**
     * Adds all the items to the list.
     */
    fun addAll(list: List<T>) {
        val oldList = this.currentNonNullList() as ArrayList<T>
        oldList.addAll(list)
        this.value = oldList
    }


    /**
     * Get the size of the list
     */
    fun size(): Int {
        if (null != this.value) {
            return this.value!!.size
        } else {
            return 0
        }
    }

    /**
     * Get the current list and if its null returns a new list.
     */
    private fun currentNonNullList(): List<T> {
        var oldList = this.value
        if (null == oldList) {
            oldList = ArrayList()
        }
        return oldList
    }
}
