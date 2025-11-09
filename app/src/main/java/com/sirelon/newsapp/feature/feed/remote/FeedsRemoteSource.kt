package com.sirelon.newsapp.feature.feed.remote

import com.sirelon.newsapp.feature.feed.remote.response.HeadlinesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class FeedsRemoteSource(private val client: HttpClient) {

    suspend fun loadHeadlines(sources: List<String>, pageSize: Int): HeadlinesResponse {
        return client.get("top-headlines") {
            parameter("pageSize", pageSize)
            if (sources.isNotEmpty()) {
                parameter("sources", sources.joinToString(separator = ","))
            }
        }.body()
    }
}
