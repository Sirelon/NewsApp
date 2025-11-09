package com.sirelon.newsapp.di

import com.sirelon.newsapp.data.remote.NewsApiService
import com.sirelon.newsapp.data.repository.DefaultNewsRepository
import com.sirelon.newsapp.data.repository.NewsRepository
import com.sirelon.newsapp.presentation.NewsViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = false
                        isLenient = true
                    }
                )
            }
            install(Logging) {
                level = LogLevel.INFO
            }
        }
    }
    single { NewsApiService(get()) }
}

val repositoryModule = module {
    single<NewsRepository> { DefaultNewsRepository(get()) }
}

val viewModelModule = module {
    viewModel { NewsViewModel(get()) }
}
