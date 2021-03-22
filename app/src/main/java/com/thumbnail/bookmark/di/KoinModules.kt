package com.thumbnail.bookmark.di

import com.thumbnail.bookmark.presenter.viewmodel.BookmarkViewModel
import com.thumbnail.bookmark.presenter.viewmodel.SearchViewModel
import com.thumbnail.data.RetrofitWebClient
import com.thumbnail.domain.repositories.BookmarkRepository
import com.thumbnail.domain.repositories.ThumbnailRepository
import com.thumbnail.data.model.web.WebApi
import com.thumbnail.data.repository.BookmarkRepositoryImpl
import com.thumbnail.data.repository.ThumbnailRepositoryImpl
import com.thumbnail.data.source.BookmarkDataSource
import com.thumbnail.data.source.ImageThumbnailDataSource
import com.thumbnail.data.source.VideoThumbnailDataSource
import com.thumbnail.data.source.impl.BookmarkDataSourceImpl
import com.thumbnail.data.source.impl.ImageThumbnailDataSourceImpl
import com.thumbnail.data.source.impl.VideoThumbnailDataSourceImpl
import com.thumbnail.domain.usecases.ChangeBookmark
import com.thumbnail.domain.usecases.DeleteBookmark
import com.thumbnail.domain.usecases.GetBookmark
import com.thumbnail.domain.usecases.GetThumbnails
import com.thumbnail.domain.usecases.HasMoreThumbnail
import com.thumbnail.domain.usecases.SaveBookmark
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class KoinModules {
    companion object {
        private val retrofitWebClient =
            RetrofitWebClient("https://dapi.kakao.com/v2/")
        private val webApi = retrofitWebClient.create(
            WebApi::class.java)

        val dataSource = module {
            factory<ImageThumbnailDataSource> { ImageThumbnailDataSourceImpl(webApi) }
            factory<VideoThumbnailDataSource> { VideoThumbnailDataSourceImpl(webApi) }
            factory<BookmarkDataSource> { BookmarkDataSourceImpl() }
        }

        val repositories = module {
            single<ThumbnailRepository> {
                ThumbnailRepositoryImpl(
                    get(),
                    get()
                )
            }

            single<BookmarkRepository> {
                BookmarkRepositoryImpl(get())
            }
        }

        val useCase = module {
            factory { GetThumbnails(get()) }
            factory { HasMoreThumbnail(get()) }
            factory { SaveBookmark(get()) }
            factory { GetBookmark(get()) }
            factory { DeleteBookmark(get()) }
            factory { ChangeBookmark(get()) }
        }

        val viewModels = module {
            viewModel { SearchViewModel(get(), get(), get()) }
            viewModel { BookmarkViewModel(get(), get(), get()) }
        }
    }
}


