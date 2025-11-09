package com.sirelon.newsapp.feature.feed.presentation

import androidx.lifecycle.ViewModel
import com.sirelon.newsapp.feature.feed.domain.FeedRepository

internal class FeedViewModel(
    private val repository: FeedRepository,
) : ViewModel() {
}