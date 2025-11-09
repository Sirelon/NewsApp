package com.sirelon.newsapp.feature.feed.local

import com.sirelon.newsapp.feature.feed.domain.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged

internal class FeedsLocalSource {

    private val articlesFlow = MutableSharedFlow<List<Article>>(replay = 1)

    suspend fun storeArticles(articles: List<Article>) {
        articlesFlow.emit(articles)
    }

    // TODO: cache sources with data
    fun getArticles(sources: List<String>): Flow<List<Article>> =
        articlesFlow.distinctUntilChanged()
}
