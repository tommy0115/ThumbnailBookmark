package com.thumbnail.bookmark.model.mapper

import com.thumbnail.domain.models.ThumbnailModel
import com.thumbnail.bookmark.model.ThumbnailItem
import com.thumbnail.bookmark.model.ThumbnailType.Companion.toImageType
import com.thumbnail.bookmark.model.ThumbnailType.Companion.toThumbnailType

class ThumbnailItemConverter {
    companion object {
        fun ThumbnailModel.toThumbnailItem(): ThumbnailItem {
            return ThumbnailItem(
                id, imageType.toThumbnailType(), title, des, url, imageUrl, dateTime
            )
        }

        fun ThumbnailItem.toThumbnailModel(): ThumbnailModel {
            return ThumbnailModel(
                type.toImageType(), id, title, des?:"", url?:"", imageUrl?:"", dateTime
            )
        }
    }
}