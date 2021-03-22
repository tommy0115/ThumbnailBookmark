package com.thumbnail.data.model.web.mapper

import com.thumbnail.data.model.web.entites.DocVideo
import com.thumbnail.domain.models.ImageType
import com.thumbnail.domain.models.ThumbnailModel
import java.util.*

class VideoDocumentToThumbnailModel {
    companion object {
        fun DocVideo.toThumbnailModel(): ThumbnailModel {
            return ThumbnailModel(
                ImageType.VIDEO,
                UUID.randomUUID().toString(),
                this.title,
                this.author,
                this.url,
                this.thumbnail,
                this.datetime
            )
        }
    }
}