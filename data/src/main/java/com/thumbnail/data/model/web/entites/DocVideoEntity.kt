package com.thumbnail.data.model.web.entites

import com.google.gson.annotations.SerializedName

data class DocVideoEntity(
    @SerializedName("documents")
    val docVideos: List<DocVideo>?,
    @SerializedName("meta")
    val meta: Meta
)
