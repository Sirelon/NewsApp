package com.sirelon.newsapp.feature.feed.domain

import com.sirelon.newsapp.feature.feed.domain.mapper.HeadlinesMapper
import com.sirelon.newsapp.feature.feed.local.FeedsLocalSource
import com.sirelon.newsapp.feature.feed.remote.FeedsRemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

internal class FeedRepository(
    private val localSource: FeedsLocalSource,
    private val remoteSource: FeedsRemoteSource,
    private val headlinesMapper: HeadlinesMapper,
) {

    fun topHeadlines(): Flow<List<Article>> {
        val sources = listOf("abc-news", "associated-press", "independent")

        return localSource
            .getArticles(sources)
            .onStart {
                val response = remoteSource.loadHeadlines(sources = sources)
                val articles = headlinesMapper.map(response)
                localSource.storeArticles(articles)
            }
    }
}
