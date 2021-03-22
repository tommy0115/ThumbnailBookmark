package com.thumbnail.bookmark

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.thumbnail.bookmark.di.KoinModules

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                KoinModules.useCase,
                KoinModules.viewModels,
                KoinModules.repositories,
                KoinModules.dataSource
            )
        }
    }
}