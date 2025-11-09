package com.sirelon.newsapp.feature.feed.domain

import com.sirelon.newsapp.base.withRefreshAction
import com.sirelon.newsapp.feature.feed.domain.mapper.HeadlinesMapper
import com.sirelon.newsapp.feature.feed.local.FeedsLocalSource
import com.sirelon.newsapp.feature.feed.remote.FeedsRemoteSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class FeedRepository(
    private val localSource: FeedsLocalSource,
    private val remoteSource: FeedsRemoteSource,
    private val headlinesMapper: HeadlinesMapper,
) {

    fun topHeadlines(): Flow<TopHeadlines> {
        val sources = listOf("abc-news", "associated-press", "independent")

        return localSource
            .getArticles(sources)
            .map { TopHeadlines(articles = it, fromNet = false) }
            .withRefreshAction {
                val articles = refreshArticles(sources)
                TopHeadlines(articles = articles, fromNet = true)
            }
    }

    private suspend fun refreshArticles(sources: List<String>): List<Article> {
        val response = remoteSource.loadHeadlines(sources = sources)
        // TODO: just to simulate network delay
        delay(1_000)

        val articles = headlinesMapper.map(response)
        localSource.storeArticles(articles)
        return articles
    }
}
