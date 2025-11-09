package com.sirelon.newsapp.feature.feed.presentation

internal sealed interface FeedContract {

    data class State(
        val list: List<String>,
    )

    sealed interface Event {

    }

    sealed interface Effect {

    }
}