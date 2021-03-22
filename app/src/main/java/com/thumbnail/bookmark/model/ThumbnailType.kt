package com.thumbnail.bookmark.model

import com.thumbnail.domain.models.ImageType

enum class ThumbnailType(val type : Int) {
    IMAGE(1), VIDEO(2);

    companion object {
        fun ImageType.toThumbnailType() : ThumbnailType{
            return when(this){
                ImageType.IMAGE -> IMAGE
                ImageType.VIDEO -> VIDEO
            }
        }

        fun ThumbnailType.toImageType() : ImageType {
            return when(this){
                IMAGE -> ImageType.IMAGE
                VIDEO -> ImageType.VIDEO
            }
        }
    }
}