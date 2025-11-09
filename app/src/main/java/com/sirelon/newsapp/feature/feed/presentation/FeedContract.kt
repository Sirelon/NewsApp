package com.sirelon.newsapp.feature.feed.presentation

import com.sirelon.newsapp.feature.feed.domain.Article

internal sealed interface FeedContract {

    data class State(
        val articles: List<Article>,
        val isRefreshing: Boolean,
    )

    sealed interface Event {
        data object Refresh: Event
    }

    sealed interface Effect {

    }
}