package com.sirelon.newsapp.feature.feed.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sirelon.newsapp.R
import com.sirelon.newsapp.feature.feed.domain.Article
import com.sirelon.newsapp.ui.theme.AppDimens
import org.koin.compose.viewmodel.koinViewModel

private const val ARTICLE_ITEM_CT = "article_item"

@Composable
fun FeedScreen() {
    val viewModel = koinViewModel<FeedViewModel>()

    val state by viewModel.state.collectAsStateWithLifecycle()

    FeedScreenContent(state = state, onEvent = viewModel::onEvent)
}


@Composable
private fun FeedScreenContent(state: FeedContract.State, onEvent: (FeedContract.Event) -> Unit) {
    Scaffold { paddingValues ->
        if (state.isLoading && state.articles.isEmpty()) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(AppDimens.Spacing.m),
            )
        } else {
            val arrangement = Arrangement.spacedBy(AppDimens.Spacing.xl3)
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = AppDimens.Size.xl15),
                contentPadding = paddingValues,
                verticalArrangement = arrangement,
                horizontalArrangement = arrangement,
            ) {
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

}

@Composable
private fun ArticleItem(modifier: Modifier, data: Article) {
    Card(modifier = modifier) {
        Column {
            // TODO: Add coil dependency
            if (data.image != null) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(AppDimens.Size.xl10),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Image for article",
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    error = painterResource(id = R.drawable.ic_launcher_background)
                )
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
