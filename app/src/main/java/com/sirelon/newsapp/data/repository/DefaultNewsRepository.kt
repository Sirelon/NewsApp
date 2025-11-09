package com.sirelon.newsapp.data.repository

import com.sirelon.newsapp.data.remote.NewsApiService
import com.sirelon.newsapp.domain.model.Article

class DefaultNewsRepository(
    private val apiService: NewsApiService
) : NewsRepository {
    override suspend fun getLatestArticles(): List<Article> =
        apiService.fetchArticles().map { dto ->
            Article(
                id = dto.id,
                title = dto.title,
                description = dto.body
            )
        }
}
