package com.thumbnail.data.repository

import com.thumbnail.data.model.ThumbnailExceptions
import com.thumbnail.data.source.ImageThumbnailDataSource
import com.thumbnail.data.source.ThumbnailDataSource
import com.thumbnail.data.source.VideoThumbnailDataSource
import com.thumbnail.domain.models.ImageType
import com.thumbnail.domain.models.ThumbnailModel
import com.thumbnail.domain.repositories.ThumbnailRepository
import kotlinx.coroutines.flow.*

class ThumbnailRepositoryImpl(
    private val imageDocDataSource: ImageThumbnailDataSource,
    private val videoDocDataSource: VideoThumbnailDataSource
) : ThumbnailRepository {

    companion object {
        const val GET_SIZE = 30
    }

    private var query: String = ""
    private var index: Int = 1

    private var isEndMap = mutableMapOf<ImageType, Int?>()
    private var thumbnailDataSourceThrowable = mutableListOf<ThrowableWrap>()

    override fun hasOccuredAllError(): Boolean {
        return thumbnailDataSourceThrowable.count() == 2
    }

    private fun clearThrowable() {
        thumbnailDataSourceThrowable.clear()
    }

    private fun clearIsEndMap() {
        isEndMap[ImageType.IMAGE] = null
        isEndMap[ImageType.VIDEO] = null
    }

    private fun isSameCurrentQuery(query: String): Boolean {
        return this.query == query
    }

    private fun setIsEnd(imageType: ImageType, index: Int) {
        isEndMap[imageType] = index
    }

    private fun getIsEndIndex(imageType: ImageType): Int? {
        return isEndMap[imageType]
    }

    private fun isEnd(index: Int, imageType: ImageType) : Boolean {
        return getIsEndIndex(imageType)?.let { it <= index } ?: false
    }

    private suspend fun fetchThumbnail(
        thumbnailDataSource: ThumbnailDataSource,
        imageType: ImageType
    ) = thumbnailDataSource.fetchThumbnail(query, index, GET_SIZE).catch { e ->
        when (e) {
            is ThumbnailExceptions.EndItemThumbnailException -> {
                if (getIsEndIndex(imageType) == null) {
                    setIsEnd(imageType, index + 1)
                }
            }
            else -> {
                thumbnailDataSourceThrowable.add(ThrowableWrap(imageType, e))
            }
        }
    }

    override suspend fun getThumbnails(query: String, index: Int): Flow<List<ThumbnailModel>> {

        clearThrowable()

        if (!isSameCurrentQuery(query)) {
            this.query = query
            clearIsEndMap()
        }

        this.index = index

        val fetchList = mutableListOf<Flow<List<ThumbnailModel>>>()
        if (!isEnd(index, ImageType.IMAGE)) {
            fetchList.add(fetchThumbnail(imageDocDataSource, ImageType.IMAGE))
        }
        if (!isEnd(index, ImageType.VIDEO)) {
            fetchList.add(fetchThumbnail(videoDocDataSource, ImageType.VIDEO))
        }

        return fetchList.merge()
    }

    override fun getLatestErrors(): Throwable? {
        return thumbnailDataSourceThrowable.lastOrNull()?.throwable
    }

    override fun hasMoreThumbnails(index: Int): Boolean {
        val group = thumbnailDataSourceThrowable.groupBy { it.imageType }
        val isFinishImage = group[ImageType.IMAGE] != null || isEnd(index, ImageType.IMAGE)
        val isFinishVideo = group[ImageType.VIDEO] != null || isEnd(index, ImageType.VIDEO)
        return !isFinishImage || !isFinishVideo
    }

    private data class ThrowableWrap(val imageType: ImageType, val throwable: Throwable)

}