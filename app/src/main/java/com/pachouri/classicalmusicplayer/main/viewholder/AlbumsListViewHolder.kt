package com.pachouri.classicalmusicplayer.main.viewholder

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pachouri.classicalmusicplayer.R
import com.pachouri.classicalmusicplayer.infrastructure.api.response.albums.Item
import com.pachouri.classicalmusicplayer.util.imageloader.AppImageView
import com.pachouri.classicalmusicplayer.util.imageloader.ImageLoader

/**
 * Created by Ankit Pachouri on 19/04/19.
 */

class AlbumsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var imageView: AppImageView
    var textViewTitle: AppCompatTextView
    var textViewSubtitle: AppCompatTextView

    init {
        imageView = itemView.findViewById(R.id.imageView)
        textViewTitle = itemView.findViewById(R.id.textViewTitle)
        textViewSubtitle = itemView.findViewById(R.id.textViewSubtitle)
    }

    fun bindView(item: Item) {
        ImageLoader.getInstance().load(
            imageView,
            item.getImages()?.get(0)?.getUrl().toString(),
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background
        )

        textViewTitle.setText(item.getName())
        textViewSubtitle.setText(itemView.context.getString(R.string.release_dated) + item.getReleaseDate())
    }

    companion object {
        fun create(parent: ViewGroup): AlbumsListViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_albums, parent, false)
            return AlbumsListViewHolder(itemView)
        }
    }
}