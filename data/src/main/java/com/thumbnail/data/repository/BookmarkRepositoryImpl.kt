package com.thumbnail.data.repository


import com.thumbnail.data.source.BookmarkDataSource
import com.thumbnail.domain.models.ThumbnailModel
import com.thumbnail.domain.repositories.BookmarkRepository
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow

class BookmarkRepositoryImpl(private val bookmarkDataSource: BookmarkDataSource) :
    BookmarkRepository {

    override suspend fun getThumbnails(): Flow<List<ThumbnailModel>> {
        return bookmarkDataSource.fetchThumbnails()
    }

    override suspend fun saveThumbnail(thumbnailModel: ThumbnailModel) : Boolean{
        return bookmarkDataSource.saveThumbnail(thumbnailModel)
    }

    override fun changeThumbnail(): ReceiveChannel<List<ThumbnailModel>> {
        return bookmarkDataSource.receiveChangeBookmark()
    }

    override suspend fun deleteThumbnail(thumbnailModel: ThumbnailModel) : Boolean{
        return bookmarkDataSource.deleteThumbnail(thumbnailModel)
    }

    override suspend fun deleteThumbnail(id: String) : Boolean{
        return bookmarkDataSource.deleteThumbnail(id)
    }

    override suspend fun deleteAll() {
        bookmarkDataSource.deleteAll()
    }
}