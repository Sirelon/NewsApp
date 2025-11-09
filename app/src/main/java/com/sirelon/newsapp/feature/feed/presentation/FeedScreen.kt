package com.sirelon.newsapp.feature.feed.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sirelon.newsapp.feature.feed.domain.Article
import com.sirelon.newsapp.ui.theme.AppDimens
import org.koin.compose.viewmodel.koinViewModel

private const val ARTICLE_ITEM_CT = "article_item"
private const val LOADING_ITEM_CT = "loading_item"

@Composable
fun FeedScreen() {
    val viewModel = koinViewModel<FeedViewModel>()

    val state by viewModel.state.collectAsStateWithLifecycle()

    FeedScreenContent(state = state, onEvent = viewModel::onEvent)
}


@Composable
private fun FeedScreenContent(state: FeedContract.State, onEvent: (FeedContract.Event) -> Unit) {
    Scaffold() {
        val arrangement = Arrangement.spacedBy(AppDimens.Spacing.xl3)
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = AppDimens.Size.xl15),
            contentPadding = it,
            verticalArrangement = arrangement,
            horizontalArrangement = arrangement,
        ) {

            stickyHeader(key = LOADING_ITEM_CT, contentType = LOADING_ITEM_CT) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppDimens.Spacing.m),
                )
            }

            items(
                items = state.articles,
                key = { it.id },
                contentType = { ARTICLE_ITEM_CT }
            ) {
                ArticleItem(modifier = Modifier.animateItem(), data = it)
            }
        }
    }

}

@Composable
private fun ArticleItem(modifier: Modifier, data: Article) {
    Card(modifier = modifier) {
        // TODO: Implement some fancy design

        Text(text = data.title)
    }
}

@Preview
@Composable
private fun FeedScreenContentPreview(modifier: Modifier = Modifier) {
    val (state, setState) = remember {
        mutableStateOf(
            value = FeedContract.State(articles = emptyList(), isLoading = true)
        )
    }

    FeedScreenContent(
        state = state,
        onEvent = {

        },
    )
}