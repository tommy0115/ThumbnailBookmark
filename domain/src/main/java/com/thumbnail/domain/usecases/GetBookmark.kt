package com.thumbnail.domain.usecases

import com.thumbnail.domain.models.ThumbnailModel
import com.thumbnail.domain.repositories.BookmarkRepository
import kotlinx.coroutines.flow.Flow

class GetBookmark(private val bookmarkRepository: BookmarkRepository) {
    suspend operator fun invoke(): Flow<List<ThumbnailModel>> {
        return bookmarkRepository.getThumbnails()
    }
}