package com.sirelon.newsapp

import android.app.Application
import com.sirelon.newsapp.di.networkModule
import com.sirelon.newsapp.di.repositoryModule
import com.sirelon.newsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.core.context.startKoin

class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            val logLevel = if (BuildConfig.DEBUG) Level.INFO else Level.NONE
            androidLogger(logLevel)
            androidContext(this@NewsApp)
            modules(
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}
