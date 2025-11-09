package com.sirelon.newsapp

import android.app.Application
import com.sirelon.newsapp.di.networkModule
import com.sirelon.newsapp.feature.feed.feedModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@NewsApp)
            modules(
                networkModule,
                feedModule,
            )
        }
    }
}
