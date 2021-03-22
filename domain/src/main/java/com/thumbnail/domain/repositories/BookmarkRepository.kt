package com.thumbnail.domain.repositories

import com.thumbnail.domain.models.ThumbnailModel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    suspend fun getThumbnails(): Flow<List<ThumbnailModel>>
    suspend fun saveThumbnail(thumbnailModel: ThumbnailModel) : Boolean
    fun changeThumbnail() : ReceiveChannel<List<ThumbnailModel>>
    suspend fun deleteThumbnail(thumbnailModel: ThumbnailModel) : Boolean
    suspend fun deleteThumbnail(id: String) : Boolean
    suspend fun deleteAll()
}