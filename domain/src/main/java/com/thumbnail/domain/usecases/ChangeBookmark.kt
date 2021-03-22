package com.thumbnail.domain.usecases

import com.thumbnail.domain.repositories.BookmarkRepository

class ChangeBookmark(private val bookmarkRepository: BookmarkRepository) {
    operator fun invoke() = bookmarkRepository.changeThumbnail()
}