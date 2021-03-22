package com.thumbnail.data.model

sealed class ThumbnailResponse<out R> {
    data class Success<out T>(val data: T, val isEnd: Boolean) : ThumbnailResponse<T>()
    data class Error(val exception: Throwable) : ThumbnailResponse<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data $isEnd]"
            is Error -> "Error[exception=$exception]"
        }
    }
}