package com.pachouri.classicalmusicplayer.util

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Ankit Pachouri on 19/04/19.
 */
class VerticalListItemDecoration(context: Context, space: Float, padding: Float) : RecyclerView.ItemDecoration() {

    private val mSpace = CommonUtils.convertDpToPixel(context, space)
    private val mPadding = CommonUtils.convertDpToPixel(context, padding)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)

        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = mPadding
        }

        if (position == parent.adapter!!.itemCount - 1) {
            outRect.bottom = mPadding
        } else {
            outRect.bottom = mSpace
        }

        outRect.left = mSpace
        outRect.right = mSpace
    }
}