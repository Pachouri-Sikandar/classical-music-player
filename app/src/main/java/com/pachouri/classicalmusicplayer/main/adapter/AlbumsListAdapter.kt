package com.pachouri.classicalmusicplayer.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.pachouri.classicalmusicplayer.infrastructure.api.response.albums.Item
import com.pachouri.classicalmusicplayer.main.viewholder.AlbumsListViewHolder
import com.pachouri.classicalmusicplayer.util.HeaderFooterRecyclerViewAdapter

/**
 * Created by Ankit Pachouri on 19/04/19.
 */
class AlbumsListAdapter :
    HeaderFooterRecyclerViewAdapter<RecyclerView.ViewHolder, AlbumsListViewHolder, RecyclerView.ViewHolder>() {

    private val mList: List<Item> = ArrayList()

    override fun onCreateHeaderViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return null!!
    }

    override fun onCreateContentViewHolder(parent: ViewGroup, viewType: Int): AlbumsListViewHolder {
        return AlbumsListViewHolder.create(parent)
    }

    override fun onCreateFooterViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return null!!
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun onBindContentViewHolder(holder: AlbumsListViewHolder, position: Int) {
        holder.bindView(mList.get(position))
    }

    override fun onBindFooterViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getHeaderCount(): Int {
        return 0
    }

    override fun getContentCount(): Int {
        return mList.size
    }

    override fun getFooterCount(): Int {
        return 0
    }

    fun setList(newList: List<Item>): AlbumsListAdapter {
        val list = mList as ArrayList
        list.clear()
        list.addAll(newList)
        mList.clear()
        mList.addAll(newList)
        notifyDataSetChanged()
        return this
    }
}