package com.sirelon.newsapp.feature.feed.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class HeadlinesResponse(
    @SerialName("status")
    val status: String?,
    @SerialName("totalResults")
    val totalResults: Int?,
    @SerialName("articles")
    val articles: List<ArticleResponse>?
)

