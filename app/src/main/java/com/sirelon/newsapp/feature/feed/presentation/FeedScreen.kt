package com.sirelon.newsapp.feature.feed.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sirelon.newsapp.common.openChromeTab
import com.sirelon.newsapp.feature.feed.domain.Article
import com.sirelon.newsapp.ui.components.NetworkImage
import com.sirelon.newsapp.ui.theme.AppDimens
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel

private const val ARTICLE_ITEM_CT = "article_item"
private const val LOADING_ITEM_CT = "loading_item"

@Composable
fun FeedScreen() {
    val viewModel = koinViewModel<FeedViewModel>()

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.effects.collectLatest { effect ->
            when (effect) {
                is FeedContract.Effect.OpenUrl -> context.openChromeTab(effect.url)
            }
        }
    }

    FeedScreenContent(state = state, onEvent = viewModel::onEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FeedScreenContent(state: FeedContract.State, onEvent: (FeedContract.Event) -> Unit) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(title = { Text(text = "Top Headlines") }, scrollBehavior = scrollBehavior)
        }
    ) { paddingValues ->
        val arrangement = Arrangement.spacedBy(AppDimens.Spacing.xl3)

        PullToRefreshBox(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
            state = rememberPullToRefreshState(),
            isRefreshing = state.isLoading,
            onRefresh = {
                onEvent(FeedContract.Event.Refresh)
            },
        ) {
            val layoutDirection = LocalLayoutDirection.current
            val contentPadding = remember(layoutDirection, paddingValues) {
                PaddingValues(
                    start = paddingValues.calculateStartPadding(layoutDirection),
                    end = paddingValues.calculateEndPadding(layoutDirection),
                    bottom = paddingValues.calculateBottomPadding()
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = AppDimens.Size.xl21),
                contentPadding = contentPadding,
                verticalArrangement = arrangement,
                horizontalArrangement = arrangement,
            ) {
                if (state.isLoading) {
                    stickyHeader(key = LOADING_ITEM_CT, contentType = LOADING_ITEM_CT) {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(AppDimens.Spacing.m),
                        )
                    }
                }

                items(items = state.articles, key = { it.id }, contentType = { ARTICLE_ITEM_CT }) { article ->
                    ArticleItem(
                        modifier = Modifier.animateItem(),
                        data = article,
                        onClick = {
                            article.url?.let { url ->
                                onEvent(FeedContract.Event.ArticleClicked(url))
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ArticleItem(modifier: Modifier, data: Article, onClick: () -> Unit) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
    ) {
        Column {
            if (data.image != null) {
                NetworkImage(url = data.image)
            }

            Text(
                modifier = Modifier.padding(AppDimens.Spacing.m),
                text = data.title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                modifier = Modifier.padding(
                    start = AppDimens.Spacing.m,
                    end = AppDimens.Spacing.m,
                    bottom = AppDimens.Spacing.m
                ),
                text = data.overview,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                modifier = Modifier.padding(
                    start = AppDimens.Spacing.m,
                    end = AppDimens.Spacing.m,
                    bottom = AppDimens.Spacing.m
                ),
                text = "Author: ${data.author}",
                style = MaterialTheme.typography.labelSmall
            )
        }
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
