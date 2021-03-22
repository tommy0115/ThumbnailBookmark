package com.thumbnail.data.model


object ThumbnailExceptions {
    abstract class ThumbnailException(message: String) : Exception(message)
    class EndItemThumbnailException(message: String = "마지막 결과 입니다.") : ThumbnailException(message)
    class SearchFailedException(message: String = "검색 결과가 없습니다.") : ThumbnailException(message)
    class NotFountItemException(message: String = "검색 결과를 찾을 수 없습니다.") : ThumbnailException(message)
    class BadRequestException(message: String = "잘못된 요청입니다. 잠시 후 다시 시도하세요.") : ThumbnailException(message)
}