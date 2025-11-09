package com.sirelon.newsapp.feature.feed.domain

data class TopHeadlines(
    val articles: List<Article>,
    val fromNet: Boolean,
)