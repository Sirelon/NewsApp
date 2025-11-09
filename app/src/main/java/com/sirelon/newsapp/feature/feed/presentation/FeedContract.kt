package com.sirelon.newsapp.feature.feed.presentation

import androidx.annotation.StringRes
import com.sirelon.newsapp.feature.feed.domain.Article

internal sealed interface FeedContract {

    data class State(
        val articles: List<Article>,
        val isRefreshing: Boolean,
    )

    sealed interface Event {
        data object Refresh : Event
        data class ArticleClicked(val url: String) : Event
    }

    sealed interface Effect {
        data class OpenUrl(val url: String) : Effect
        data class ShowSnackbar(@StringRes val messageRes: Int) : Effect
        data object Idle : Effect
    }
}
