package com.sirelon.newsapp.feature.feed.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FeedScreen() {

    val viewModel = koinViewModel<FeedViewModel>()

    Scaffold() {
        Box(modifier = Modifier.padding(it))
    }

}