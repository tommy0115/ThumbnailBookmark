package com.thumbnail.bookmark.presenter.view.adapter

import android.view.View
import com.thumbnail.bookmark.model.ThumbnailItem

interface SearchAdapterHandler {
    fun onClickItem(view: View, thumbnailItem: ThumbnailItem)
}