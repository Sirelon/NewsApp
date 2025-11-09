package com.sirelon.newsapp.di


import com.sirelon.newsapp.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

private const val API_BASE_URL = "https://newsapi.org/v2/"

val networkModule = module {
    single {
        createHttpClient()
    }
}

private fun createHttpClient(): HttpClient = HttpClient(OkHttp) {
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

    defaultRequest {
        url(API_BASE_URL)
        url {
            header(HttpHeaders.Accept, ContentType.Application.Json)
            parameters.append("apiKey", BuildConfig.NEWS_API_KEY)
        }
    }
}
