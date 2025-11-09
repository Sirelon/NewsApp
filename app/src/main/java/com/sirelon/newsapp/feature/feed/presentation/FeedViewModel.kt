package com.sirelon.newsapp.feature.feed.presentation

import com.sirelon.newsapp.base.BaseViewModel
import com.sirelon.newsapp.feature.feed.domain.FeedRepository

internal class FeedViewModel(
    private val repository: FeedRepository,
) : BaseViewModel<FeedContract.State, FeedContract.Event, FeedContract.Effect>() {

    override fun initialState(): FeedContract.State = FeedContract.State(emptyList())

    override fun onEvent(event: FeedContract.Event) {
        // TODO:
    }
}