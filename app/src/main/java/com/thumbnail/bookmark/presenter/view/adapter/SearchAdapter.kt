package com.thumbnail.bookmark.presenter.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thumbnail.bookmark.databinding.ListSearchItemBinding
import com.thumbnail.bookmark.model.ThumbnailItem

class SearchAdapter(private val handler: SearchAdapterHandler) :
    ListAdapter<ThumbnailItem, RecyclerView.ViewHolder>(ThumbnailDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val thumbnailItem = getItem(position)
        (holder as ThumbnailViewHolder).bind(thumbnailItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ThumbnailViewHolder(
            ListSearchItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].type.type
    }

    inner class ThumbnailViewHolder(private val binding: ListSearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ThumbnailItem) {
            binding.apply {
                thumbnail = item
                handler = this@SearchAdapter.handler
                executePendingBindings()
            }
        }
    }
}

private class ThumbnailDiffCallback : DiffUtil.ItemCallback<ThumbnailItem>() {
    override fun areItemsTheSame(oldItem: ThumbnailItem, newItem: ThumbnailItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ThumbnailItem, newItem: ThumbnailItem): Boolean {
        return oldItem == newItem
    }
}