package com.thumbnail.bookmark.model

import com.thumbnail.data.util.ZonedDateTimeUtil.Companion.toMillisecond
import com.thumbnail.data.util.ZonedDateTimeUtil.Companion.toZonedDateTime
import java.text.SimpleDateFormat
import java.util.*

val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA)

data class ThumbnailItem(
    val id : String,
    val type: ThumbnailType,
    val title: String,
    val des : String?,
    val url : String?,
    val imageUrl: String?,
    val dateTime: String = System.currentTimeMillis().toZonedDateTime()
){
    fun getDateTimeText() : String{
        return format.format(Date(dateTime.toMillisecond()))
    }
}