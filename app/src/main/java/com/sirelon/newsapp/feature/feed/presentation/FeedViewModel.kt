package com.sirelon.newsapp.feature.feed.presentation

import androidx.lifecycle.viewModelScope
import com.sirelon.newsapp.base.BaseViewModel
import com.sirelon.newsapp.feature.feed.domain.FeedRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class)
internal class FeedViewModel(
    repository: FeedRepository,
) : BaseViewModel<FeedContract.State, FeedContract.Event, FeedContract.Effect>() {

    private val refreshEmitter = MutableStateFlow(0)

    init {
        // TODO: show progress for network fetching.
        refreshEmitter
            .flatMapLatest {
                repository.topHeadlines()
            }
            .onStart { setState { it.copy(isLoading = true) } }
            .onEach { articles ->
                setState { it.copy(articles = articles, isLoading = false) }
            }
            .catch {
                // TODO: handle error
                it.printStackTrace()
                setState { it.copy(isLoading = false) }
            }
            .launchIn(viewModelScope)
    }

    override fun initialState(): FeedContract.State =
        FeedContract.State(
            articles = emptyList(),
            isLoading = false,
        )

    override fun onEvent(event: FeedContract.Event) {
        when (event) {
            FeedContract.Event.Refresh -> refreshEmitter.update { it + 1 }
        }
    }
}