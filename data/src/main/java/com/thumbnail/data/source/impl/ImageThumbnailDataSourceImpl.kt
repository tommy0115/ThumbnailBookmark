package com.thumbnail.data.source.impl

import android.util.Log
import com.thumbnail.data.model.ThumbnailExceptions
import com.thumbnail.data.model.web.WebApi
import com.thumbnail.data.model.web.mapper.ImageDocumentToThumbnailModel.Companion.toThumbnailModel
import com.thumbnail.data.source.ImageThumbnailDataSource
import com.thumbnail.domain.models.ThumbnailModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class ImageThumbnailDataSourceImpl(private val webApi: WebApi) : ImageThumbnailDataSource {

    override suspend fun fetchThumbnail(
            query: String,
            index: Int,
            size: Int
    ): Flow<List<ThumbnailModel>> {
        return flow {
            try {
                webApi.searchImage(query, index, size).let { doc ->
                    emit(doc.docImages?.map { it.toThumbnailModel() }
                            ?: throw ThumbnailExceptions.NotFountItemException())

                    if (doc.meta.isEnd) {
                        throw ThumbnailExceptions.EndItemThumbnailException()
                    }
                }
            } catch (e: HttpException) {
                e.printStackTrace()
                Log.e("TAG", "ImageThumbnailDataSource")
                throw ThumbnailExceptions.BadRequestException()
            } catch (e: Exception) {
                Log.e("TAG", "ImageThumbnailDataSource")
                e.printStackTrace()
                throw e
            }
        }
    }
}