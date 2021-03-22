package com.thumbnail.bookmark.presenter.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.thumbnail.domain.usecases.ChangeBookmark
import com.thumbnail.domain.usecases.DeleteBookmark
import com.thumbnail.domain.usecases.GetBookmark
import com.thumbnail.bookmark.model.ThumbnailItem
import com.thumbnail.bookmark.model.mapper.ThumbnailItemConverter.Companion.toThumbnailItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class BookmarkViewModel(
    changeBookmark: ChangeBookmark,
    getBookmark: GetBookmark,
    private val deleteBookmark: DeleteBookmark
) : ViewModel() {

    val getBookmarkLiveData: LiveData<List<ThumbnailItem>> = liveData {
        getBookmark().collect { model ->
            emit(model.map { it.toThumbnailItem() })
        }

        changeBookmark().receiveAsFlow().collect {
            emit(it.map { it.toThumbnailItem() })
        }
    }

    fun deleteBookmark(thumbnailItem: ThumbnailItem) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteBookmark(thumbnailItem.id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("BookmarkViewModel", "onCleared")
    }
}