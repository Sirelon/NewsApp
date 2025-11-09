package com.sirelon.newsapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

/**
 * Minimal wrapper over Coil's AsyncImage for consistent defaults.
 */
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
