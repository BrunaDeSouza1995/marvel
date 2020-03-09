package com.poc.bruna.marvel.feature.character.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.poc.bruna.marvel.R
import com.poc.bruna.marvel.utils.extensions.inflateView
import com.poc.bruna.marvel.utils.extensions.loadImage
import kotlinx.android.synthetic.main.item_list_comic.view.*

class ComicsAdapter : ListAdapter<Pair<String, String>, ViewHolder>(ComicsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflateView(R.layout.item_list_comic))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageView = itemView.itemImageComic
    private val titleView = itemView.itemTitleComic

    fun bind(pair: Pair<String, String>) {
        val (title, image) = pair
        titleView.text = title
        imageView.loadImage(image)
    }
}

class ComicsDiffCallback : DiffUtil.ItemCallback<Pair<String, String>>() {
    override fun areItemsTheSame(
        oldItem: Pair<String, String>,
        newItem: Pair<String, String>
    ): Boolean {
        return oldItem.first == newItem.first
    }

    override fun areContentsTheSame(
        oldItem: Pair<String, String>,
        newItem: Pair<String, String>
    ): Boolean {
        return oldItem == newItem
    }
}