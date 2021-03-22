package com.thumbnail.bookmark.model

sealed class UIEvent {
    object Idle : UIEvent()
    class ShowMessage(val message: String) : UIEvent()
}