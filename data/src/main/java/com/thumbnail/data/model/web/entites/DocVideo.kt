package com.thumbnail.data.model.web.entites

import com.google.gson.annotations.SerializedName

data class DocVideo(
    @SerializedName("title")
    val title : String,
    @SerializedName("url")
    val url: String,
    @SerializedName("datetime")
    val datetime: String,
    @SerializedName("play_time")
    val playTime: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("author")
    val author: String
)