package com.thumbnail.data.model.web.mapper

import com.thumbnail.data.model.web.entites.DocImage
import com.thumbnail.domain.models.ImageType
import com.thumbnail.domain.models.ThumbnailModel
import java.util.*

class ImageDocumentToThumbnailModel {
    companion object {
        fun DocImage.toThumbnailModel(): ThumbnailModel {
            return ThumbnailModel(
                ImageType.IMAGE,
                UUID.randomUUID().toString(),
                this.display_sitename,
                this.collection.toUpperCase(),
                this.doc_url,
                this.thumbnail_url,
                this.datetime
            )
        }
    }
}