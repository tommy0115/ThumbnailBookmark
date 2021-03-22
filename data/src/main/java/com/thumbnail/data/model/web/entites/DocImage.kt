package com.thumbnail.data.model.web.entites

import com.google.gson.annotations.SerializedName

data class DocImage(
    @SerializedName("collection") val collection : String,
    @SerializedName("thumbnail_url") val thumbnail_url : String,
    @SerializedName("image_url") val image_url : String,
    @SerializedName("width") val width : String,
    @SerializedName("height") val height : String,
    @SerializedName("display_sitename") val display_sitename : String,
    @SerializedName("doc_url") val doc_url: String,
    @SerializedName("datetime") val datetime: String
)

/*
collection	String	컬렉션
thumbnail_url	String	미리보기 이미지 URL
image_url	String	이미지 URL
width	Integer	이미지의 가로 길이
height	Integer	이미지의 세로 길이
display_sitename	String	출처
doc_url	String	문서 URL
datetime	Datetime	문서 작성시간, ISO 8601
[YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]*/
