package com.thumbnail.data.source

import com.thumbnail.domain.models.ThumbnailModel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow

interface BookmarkDataSource {
    suspend fun fetchThumbnails() : Flow<List<ThumbnailModel>>
    fun receiveChangeBookmark() : ReceiveChannel<List<ThumbnailModel>>
    suspend fun saveThumbnail(thumbnail : ThumbnailModel) : Boolean
    suspend fun deleteThumbnail(thumbnail : ThumbnailModel) : Boolean
    suspend fun deleteThumbnail(id : String) : Boolean
    suspend fun deleteAll()
}