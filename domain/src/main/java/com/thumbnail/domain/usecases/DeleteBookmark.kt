package com.thumbnail.domain.usecases

import com.thumbnail.domain.repositories.BookmarkRepository

class DeleteBookmark(private val bookmarkRepository: BookmarkRepository) {
    suspend operator fun invoke(id: String): Boolean {
        return bookmarkRepository.deleteThumbnail(id)
    }
}