package com.sirelon.newsapp.feature.feed.local

import com.sirelon.newsapp.feature.feed.domain.Article

internal class FeedsLocalSource {

    private var cachedArticles: List<Article> = emptyList()

    fun cacheArticles(articles: List<Article>) {
        cachedArticles = articles
    }

    fun getCachedArticles(): List<Article> = cachedArticles
}
