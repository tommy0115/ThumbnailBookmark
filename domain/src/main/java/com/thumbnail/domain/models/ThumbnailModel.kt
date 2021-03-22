package com.thumbnail.domain.models

data class ThumbnailModel(
    val imageType: ImageType,
    val id: String,
    val title: String,
    val des: String,
    val url: String,
    val imageUrl: String,
    val dateTime: String
)