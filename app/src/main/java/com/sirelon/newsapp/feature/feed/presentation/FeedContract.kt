package com.sirelon.newsapp.feature.feed.presentation

import com.sirelon.newsapp.feature.feed.domain.Article

internal sealed interface FeedContract {

    data class State(
        val articles: List<Article>,
        val isLoading: Boolean,
    )

    sealed interface Event {

    }

    sealed interface Effect {

    }
}