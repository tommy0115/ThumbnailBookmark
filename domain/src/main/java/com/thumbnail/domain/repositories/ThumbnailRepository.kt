package com.thumbnail.domain.repositories

import com.thumbnail.domain.models.ThumbnailModel
import kotlinx.coroutines.flow.Flow

interface ThumbnailRepository {
    suspend fun getThumbnails(query: String, index : Int) : Flow<List<ThumbnailModel>>
    fun getLatestErrors(): Throwable?
    fun hasMoreThumbnails(index: Int) : Boolean
    fun hasOccuredAllError() : Boolean
}