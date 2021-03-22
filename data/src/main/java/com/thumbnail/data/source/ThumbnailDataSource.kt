package com.thumbnail.data.source

import com.thumbnail.domain.models.ThumbnailModel
import kotlinx.coroutines.flow.Flow

interface ThumbnailDataSource {
    suspend fun fetchThumbnail(query: String, index : Int, size : Int) : Flow<List<ThumbnailModel>>
}