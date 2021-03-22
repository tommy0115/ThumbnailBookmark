package com.thumbnail.data

interface ApiRepository<T> {
    fun setUrl(url : String)
    fun getAPI() : T
    fun baseUrl() : String
}