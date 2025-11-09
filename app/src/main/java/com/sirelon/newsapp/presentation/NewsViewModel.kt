package com.sirelon.newsapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirelon.newsapp.data.repository.NewsRepository
import com.sirelon.newsapp.domain.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface NewsUiState {
    data object Loading : NewsUiState
    data class Success(val articles: List<Article>) : NewsUiState
    data class Error(val message: String) : NewsUiState
}

class NewsViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<NewsUiState> = MutableStateFlow(NewsUiState.Loading)
    val uiState: StateFlow<NewsUiState> = _uiState

    init {
        refresh()
    }

    fun refresh() {
        _uiState.value = NewsUiState.Loading
        viewModelScope.launch {
            runCatching { repository.getLatestArticles() }
                .onSuccess { articles ->
                    _uiState.value = NewsUiState.Success(articles)
                }
                .onFailure { throwable ->
                    _uiState.value = NewsUiState.Error(
                        throwable.message ?: "Something went wrong"
                    )
                }
        }
    }
}
