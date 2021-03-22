package com.thumbnail.domain.usecases

import com.thumbnail.domain.models.ThumbnailModel
import com.thumbnail.domain.repositories.BookmarkRepository

class SaveBookmark(private val bookmarkRepository: BookmarkRepository) {
    suspend operator fun invoke(thumbnailModel: ThumbnailModel): Boolean {
        return bookmarkRepository.saveThumbnail(thumbnailModel)
    }
}