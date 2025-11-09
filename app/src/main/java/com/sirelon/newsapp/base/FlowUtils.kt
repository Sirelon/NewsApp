package com.sirelon.newsapp.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

fun <T> Flow<T>.withRefreshAction(
    predicate: suspend () -> Boolean = { true },
    onFailure: suspend (Throwable) -> Unit = {
        throw it
    },
    action: suspend () -> T?
) = channelFlow {
    launch {
        if (predicate()) {
            runCatching { action() }
                .onSuccess { if (it != null) send(it) }
                .onFailure { onFailure(it) }
        }
    }
    this@withRefreshAction.collect { send(it) }
}