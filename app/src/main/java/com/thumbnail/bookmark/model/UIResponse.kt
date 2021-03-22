package com.thumbnail.bookmark.model

sealed class UIResponse {
    object Loading : UIResponse()
    class Error(val message : String) : UIResponse()
    object Complete : UIResponse()
}