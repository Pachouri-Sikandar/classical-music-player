package com.pachouri.classicalmusicplayer.util

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by Ankit Pachouri on 19/04/19.
 *
 * RecyclerView Adapter which holds all three types of views Header, Footer and Content
 */
abstract class HeaderFooterRecyclerViewAdapter<H : RecyclerView.ViewHolder, C : RecyclerView.ViewHolder, F : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val HEADER_TYPE = 1
    private val CONTENT_TYPE = 2
    private val FOOTER_TYPE = 3

    protected var mContext: Context? = null

    abstract fun onCreateHeaderViewHolder(parent: ViewGroup): H

    abstract fun onCreateContentViewHolder(parent: ViewGroup, viewType: Int): C

    abstract fun onCreateFooterViewHolder(parent: ViewGroup): F

    abstract fun onBindHeaderViewHolder(holder: H, position: Int)

    abstract fun onBindContentViewHolder(holder: C, position: Int)

    abstract fun onBindFooterViewHolder(holder: F, position: Int)

    abstract fun getHeaderCount(): Int

    abstract fun getContentCount(): Int

    abstract fun getFooterCount(): Int

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        if (type == HEADER_TYPE) {
            return onCreateHeaderViewHolder(parent)
        } else if (type == FOOTER_TYPE) {
            return onCreateFooterViewHolder(parent)
        }
        return onCreateContentViewHolder(parent, type)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val type = getItemViewType(position)
        if (type == HEADER_TYPE) {
            onBindHeaderViewHolder(holder as H, convertToHeaderIndex(position))
        } else if (type == FOOTER_TYPE) {
            onBindFooterViewHolder(holder as F, convertToFooterIndex(position))
        } else {
            onBindContentViewHolder(holder as C, convertToContentIndex(position))
        }
    }

    /**
     * Get total items count
     */
    override fun getItemCount(): Int {
        return getContentCount() + getHeaderCount() + getFooterCount()
    }

    /**
     * Get the itemViewType on the basis of position and header/footer counts
     */
    override fun getItemViewType(position: Int): Int {
        var offset = 0
        if (getHeaderCount() > 0) {
            offset = offset + getHeaderCount()
            if (position < getHeaderCount()) {
                return HEADER_TYPE
            }
        }
        if (getFooterCount() > 0) {
            if (position >= getContentCount() + offset) {
                return FOOTER_TYPE
            }
        }
        return CONTENT_TYPE
    }

    /**
     * Get the actual position of the content
     */
    protected fun convertToContentIndex(adapterPosition: Int): Int {
        return adapterPosition - getHeaderCount()
    }

    /**
     * Get the position of the header
     */
    protected fun convertToHeaderIndex(adapterPosition: Int): Int {
        return adapterPosition
    }

    /**
     * Get the position of the footer
     */
    protected fun convertToFooterIndex(adapterPosition: Int): Int {
        return adapterPosition - getHeaderCount() - getContentCount()
    }
}