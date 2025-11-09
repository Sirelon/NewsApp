package com.sirelon.newsapp.data.remote

import com.sirelon.newsapp.data.remote.model.ArticleDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class NewsApiService(
    private val httpClient: HttpClient
) {
    suspend fun fetchArticles(): List<ArticleDto> {
        return httpClient.get("https://jsonplaceholder.typicode.com/posts").body()
    }
}
