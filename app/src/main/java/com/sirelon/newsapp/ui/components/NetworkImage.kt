package com.sirelon.newsapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun NetworkImage(
    url: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context)
        .data(url)
        .crossfade(true)
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale
    )
}
