package com.thumbnail.data.util

import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

class ZonedDateTimeUtil {

    companion object {
        fun String.toMillisecond() : Long{
            return ZonedDateTime.parse(this, DateTimeFormatter.ISO_ZONED_DATE_TIME).toInstant()
                .toEpochMilli()
        }

        fun Long.toZonedDateTime() : String{
            return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())
                .format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
        }

    }
}