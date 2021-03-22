package com.thumbnail.domain.usecases

import com.thumbnail.domain.repositories.ThumbnailRepository

class HasMoreThumbnail(private val thumbnailRepository: ThumbnailRepository) {
    operator fun invoke(index: Int) = thumbnailRepository.hasMoreThumbnails(index)
}