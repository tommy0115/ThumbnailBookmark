package com.thumbnail.domain.usecases

import com.thumbnail.domain.models.Response
import com.thumbnail.domain.models.ThumbnailModel
import com.thumbnail.domain.repositories.ThumbnailRepository
import kotlinx.coroutines.flow.*

class GetThumbnails(private val thumbnailRepository: ThumbnailRepository) {
    suspend operator fun invoke(query: String, page: Int): Flow<Response<List<ThumbnailModel>>> =
            flow {
                emit(Response.Loading)
                val thumbnails = mutableListOf<ThumbnailModel>()
                thumbnailRepository.getThumbnails(query, page).collect {
                    thumbnails.addAll(it)
                }

                if (thumbnailRepository.hasOccuredAllError()){
                    emit(Response.Error(thumbnailRepository.getLatestErrors()!!))
                } else {
                    emit(Response.Success(thumbnails))
                }
            }
}