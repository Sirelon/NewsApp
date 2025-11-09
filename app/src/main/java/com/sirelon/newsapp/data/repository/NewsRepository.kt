package com.sirelon.newsapp.data.repository

import com.sirelon.newsapp.domain.model.Article

interface NewsRepository {
    suspend fun getLatestArticles(): List<Article>
}
