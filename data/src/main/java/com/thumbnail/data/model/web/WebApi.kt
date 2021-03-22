package com.thumbnail.data.model.web

import com.thumbnail.data.model.web.entites.DocImageEntity
import com.thumbnail.data.model.web.entites.DocVideoEntity
import retrofit2.http.*

interface WebApi {

    @GET("/v2/search/vclip")
    suspend fun searchVideoClip(
        @Query("query") query: String, // 검색을 원하는 질의어
        @Query("page") page: Int? = null, // 결과 페이지 번호, 1~15 사이의 값
        @Query("size") size: Int? = null, // 한 페이지에 보여질 문서 수, 1~30 사이의 값, 기본 값 15
        @Query("sort") sort: String? = null, // 결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy
        @Header("Authorization") token: String? = _token
    ) : DocVideoEntity

    @GET("/v2/search/image")
    suspend fun searchImage(
        @Query("query") query: String,
        @Query("page") page: Int? = null, // 결과 페이지 번호, 1~50 사이의 값, 기본 값 1
        @Query("size") size: Int? = null, // 한 페이지에 보여질 문서 수, 1~80 사이의 값, 기본 값 80
        @Query("sort") sort: String? = null, // 결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy
        @Header("Authorization") token: String? = _token
    ) : DocImageEntity

    companion object {
        private var _token : String = "KakaoAK f8ba52dcd80c764fef391af00d8d37ba"
    }
}