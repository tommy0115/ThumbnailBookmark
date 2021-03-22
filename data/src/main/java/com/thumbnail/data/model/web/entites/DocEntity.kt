package com.thumbnail.data.model.web.entites

import com.google.gson.annotations.SerializedName

abstract class DocEntity(
    @SerializedName("meta")
    open val meta: Meta
)