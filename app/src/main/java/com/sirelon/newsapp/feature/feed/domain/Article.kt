package com.sirelon.newsapp.feature.feed.domain

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
data class Article(
    val id: String,
    val title: String,
    val overview: String,
    val fullContent: String,
    val author: String,
    val url: String?,
    val image: String?,
    val publishedAt: Instant,
)
