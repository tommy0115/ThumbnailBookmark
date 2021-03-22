package com.thumbnail.bookmark.presenter.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.thumbnail.bookmark.model.*
import com.thumbnail.bookmark.model.mapper.ThumbnailItemConverter.Companion.toThumbnailItem
import com.thumbnail.bookmark.model.mapper.ThumbnailItemConverter.Companion.toThumbnailModel
import com.thumbnail.data.util.ZonedDateTimeUtil.Companion.toMillisecond
import com.thumbnail.domain.models.Response
import com.thumbnail.domain.usecases.GetThumbnails
import com.thumbnail.domain.usecases.HasMoreThumbnail
import com.thumbnail.domain.usecases.SaveBookmark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class SearchViewModel(
    private val getThumbnails: GetThumbnails,
    private val saveBookmark: SaveBookmark,
    private val hasMoreThumbnail: HasMoreThumbnail
) : ViewModel() {

    companion object {
        const val START_PAGE = 1
    }


    private var query = MutableLiveData<String>()
    private val pageLiveData = MutableLiveData(NoSelectedPage)

    private val _thumbnailEvent = MutableLiveData<UIResponse>()
    val thumbnailEvent: LiveData<UIResponse> = _thumbnailEvent

    private val _uiEvent = MutableLiveData<UIEvent>()
    val uiEvent: LiveData<UIEvent> = _uiEvent

    val thumbnails = MediatorLiveData<List<ThumbnailItem>>().apply {
        addSource(query) {
            viewModelScope.launch(Dispatchers.IO) {
                pageLiveData.postValue(PageNumber(START_PAGE))
            }
        }
        addSource(pageLiveData) {
            if (it == NoSelectedPage) {
                return@addSource
            }

            if (query.value == null){
                sendMessage("먼저 검색을 시작해주세요")
                return@addSource
            }

            viewModelScope.launch(Dispatchers.IO) {
                getThumbnails(query.value!!, it.number).collect { response ->
                    when (response) {
                        is Response.Success -> {
                            val thumbnailsCache = mutableListOf<ThumbnailItem>()
                            thumbnailsCache.addAll(response.data.map { model -> model.toThumbnailItem() }
                                .sortedByDescending { it.dateTime.toMillisecond() })
                            postValue(thumbnailsCache)
                            _thumbnailEvent.postValue(UIResponse.Complete)
                        }
                        is Response.Error -> {
                            _thumbnailEvent.postValue(
                                UIResponse.Error(
                                    response.exception.message ?: ""
                                )
                            )
                            postValue(listOf())
                        }
                        is Response.Loading -> {
                            _thumbnailEvent.postValue(UIResponse.Loading)
                        }
                    }
                }
            }
        }
    }

    val pageTextLiveData : LiveData<String> = Transformations.map(pageLiveData){
        if (it.number > 0) {
            it.number.toString()
        } else {
            "-"
        }
    }

    fun setQuery(text: String) {
        query.postValue(text)
    }

    fun next() {
        pageLiveData.value?.let {
            if (!hasMoreThumbnail(it.number + 1)) {
                sendMessage("마지막 결과 입니다.")
                return
            }
            pageLiveData.postValue(PageNumber(it.number + 1))
        }
    }

    fun before() {
        pageLiveData.value?.let {
            if (it.number - 1 <= 0 ){
                sendMessage("처음 페이지 입니다")
                return
            }
            pageLiveData.postValue(PageNumber(it.number - 1))
        }
    }

    fun saveThumbnail(thumbnail: ThumbnailItem) {
        viewModelScope.launch {
            if (saveBookmark(thumbnail.toThumbnailModel())) {
                sendMessage("보관함에 저장 되었습니다.")
            } else {
                sendMessage("저장에 실패하였습니다. 동일한 이미지가 보관함에 있는지 확인하세요.")
            }
        }
    }

    fun sendMessage(message : String){
        _uiEvent.postValue(UIEvent.ShowMessage(message))
    }

    fun setUiIdle(){
        _uiEvent.postValue(UIEvent.Idle)
    }

    override fun onCleared() {
        super.onCleared()
        pageLiveData.value = NoSelectedPage
        Log.d("ThumbnailViewModel", "onCleared")
    }
}