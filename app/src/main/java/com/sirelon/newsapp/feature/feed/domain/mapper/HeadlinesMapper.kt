package com.sirelon.newsapp.feature.feed.domain.mapper

import com.sirelon.newsapp.feature.feed.domain.Article
import com.sirelon.newsapp.feature.feed.remote.response.HeadlinesResponse
import kotlin.time.Instant

internal class HeadlinesMapper {

    fun map(response: HeadlinesResponse): List<Article> {
        return response.articles
            .orEmpty()
            .mapNotNull { articleResponse ->
                val title = articleResponse.title?.takeIf { it.isNotBlank() } ?: return@mapNotNull null
                Article(
                    title = title,
                    overview = articleResponse.description.orEmpty(),
                    fullContent = articleResponse.content ?: articleResponse.description.orEmpty(),
                    author = (articleResponse.author ?: articleResponse.source?.name).orEmpty(),
                    url = articleResponse.url,
                    image = articleResponse.urlToImage,
                    publishedAt = parseInstant(articleResponse.publishedAt),
                )
            }
    }

    private fun parseInstant(value: String?): Instant {
        return value
            ?.let { runCatching { Instant.parse(it) }.getOrNull() }
            ?: Instant.fromEpochMilliseconds(0)
    }
}
