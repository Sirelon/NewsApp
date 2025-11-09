package com.sirelon.newsapp.feature.feed

import com.sirelon.newsapp.feature.feed.domain.FeedRepository
import com.sirelon.newsapp.feature.feed.domain.mapper.HeadlinesMapper
import com.sirelon.newsapp.feature.feed.local.FeedsLocalSource
import com.sirelon.newsapp.feature.feed.presentation.FeedViewModel
import com.sirelon.newsapp.feature.feed.remote.FeedsRemoteSource
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val feedModule = module {
    // data
    factoryOf(::FeedsRemoteSource)
    factoryOf(::FeedsLocalSource)
    factoryOf(::HeadlinesMapper)

    // domain
    factoryOf(::FeedRepository)

    // ui
    viewModelOf(::FeedViewModel)
}
