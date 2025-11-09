package com.sirelon.newsapp.feature.feed.presentation

import androidx.lifecycle.viewModelScope
import com.sirelon.newsapp.R
import com.sirelon.newsapp.base.BaseViewModel
import com.sirelon.newsapp.feature.feed.domain.FeedRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class)
internal class FeedViewModel(
    private val repository: FeedRepository,
) : BaseViewModel<FeedContract.State, FeedContract.Event, FeedContract.Effect>() {

    private val refreshEmitter = MutableStateFlow(0)

    init {
        refreshEmitter
            .onEach { setState { it.copy(isRefreshing = true) } }
            .flatMapLatest { repository.topHeadlines() }
            .onEach { topHeadlines ->
                setState {
                    it.copy(
                        articles = topHeadlines.articles,
                        isRefreshing = it.isRefreshing && !topHeadlines.fromNet,
                    )
                }
            }
            .catch { throwable ->
                throwable.printStackTrace()
                setState { it.copy(isRefreshing = false) }
                postEffect(
                    FeedContract.Effect.ShowSnackbar(
                        R.string.feed_error_refresh_failed
                    )
                )
            }
            .launchIn(viewModelScope)
    }

    override fun initialState(): FeedContract.State =
        FeedContract.State(
            articles = emptyList(),
            isRefreshing = false,
        )

    override fun onEvent(event: FeedContract.Event) {
        when (event) {
            FeedContract.Event.Refresh -> refreshEmitter.update { it + 1 }
            is FeedContract.Event.ArticleClicked -> postEffect(FeedContract.Effect.OpenUrl(event.url))
        }
    }

    fun resetEffects() {
        postEffect(FeedContract.Effect.Idle)
    }
}
