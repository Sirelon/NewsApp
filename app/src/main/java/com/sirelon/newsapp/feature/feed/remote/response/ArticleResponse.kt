package com.sirelon.newsapp.feature.feed.remote.response

import com.sirelon.newsapp.feature.source.remote.SourceResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class ArticleResponse(
    @SerialName("source")
    val source: SourceResponse?,
    @SerialName("author")
    val author: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("url")
    val url: String?,
    @SerialName("urlToImage")
    val urlToImage: String?,
    @SerialName("publishedAt")
    val publishedAt: String?,
    @SerialName("content")
    val content: String?
)