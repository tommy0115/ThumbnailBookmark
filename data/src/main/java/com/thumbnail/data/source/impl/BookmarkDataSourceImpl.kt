package com.thumbnail.data.source.impl

import com.thumbnail.data.source.BookmarkDataSource
import com.thumbnail.domain.models.ThumbnailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BookmarkDataSourceImpl : BookmarkDataSource {

    private val thumbnailList = mutableListOf<ThumbnailModel>()
    private val channel = Channel<List<ThumbnailModel>>()

    override suspend fun fetchThumbnails(): Flow<List<ThumbnailModel>> {
        return flow<List<ThumbnailModel>> {
            emit(thumbnailList)
        }.flowOn(Dispatchers.IO)
    }

    override fun receiveChangeBookmark() : ReceiveChannel<List<ThumbnailModel>> {
        return channel
    }

    override suspend fun saveThumbnail(thumbnail: ThumbnailModel): Boolean {
        thumbnailList.find { it.id == thumbnail.id }?.let {
            return false
        }

        return thumbnailList.add(thumbnail).also {
            if (it) {
                channel.offer(thumbnailList)
            }
        }
    }

    override suspend fun deleteThumbnail(thumbnail: ThumbnailModel): Boolean {
        return thumbnailList.remove(thumbnail).also {
            if (it) {
                channel.offer(thumbnailList)
            }
        }
    }

    override suspend fun deleteThumbnail(id: String): Boolean {
        return (thumbnailList.find { it.id == id }?.let {
            thumbnailList.remove(it)
        } ?: false).also {
            if (it) {
                channel.offer(thumbnailList)
            }
        }
    }

    override suspend fun deleteAll() {
        return thumbnailList.clear()
    }
}