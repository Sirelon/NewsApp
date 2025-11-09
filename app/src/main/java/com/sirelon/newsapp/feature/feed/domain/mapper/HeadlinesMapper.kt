package com.sirelon.newsapp.feature.feed.domain.mapper

import com.sirelon.newsapp.feature.feed.domain.Article
import com.sirelon.newsapp.feature.feed.remote.response.ArticleResponse
import com.sirelon.newsapp.feature.feed.remote.response.HeadlinesResponse
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
internal class HeadlinesMapper {

    fun map(response: HeadlinesResponse): List<Article> {
        return response.articles
            .orEmpty()
            .mapNotNull { it.map() }
    }


    private fun ArticleResponse.map(): Article? {
        val response = this
        val title = response.title?.takeIf { it.isNotBlank() } ?: return null
        return Article(
            id = response.url ?: title,
            title = title,
            overview = response.description.orEmpty(),
            fullContent = response.content ?: response.description.orEmpty(),
            author = (response.author ?: response.source?.name).orEmpty(),
            url = response.url,
            image = response.urlToImage,
            publishedAt = parseInstant(response.publishedAt),
        )
    }

    private fun parseInstant(value: String?): Instant {
        return value
            ?.let { runCatching { Instant.parse(it) }.getOrNull() }
            ?: Instant.fromEpochMilliseconds(0)
    }
}
