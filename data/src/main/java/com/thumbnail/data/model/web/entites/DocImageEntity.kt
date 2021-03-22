package com.thumbnail.data.model.web.entites

import com.google.gson.annotations.SerializedName

data class DocImageEntity(
    @SerializedName("documents")
    val docImages: List<DocImage>?,
    @SerializedName("meta")
    val meta: Meta
)