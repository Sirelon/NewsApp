package com.sirelon.newsapp.feature.source.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SourceResponse(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?
)
