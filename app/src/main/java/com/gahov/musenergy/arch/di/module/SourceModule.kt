package com.gahov.musenergy.arch.di.module

import com.gahov.domain.component.logger.Logger
import com.gahov.musenergy.data.local.storage.articles.ArticlesDao
import com.gahov.musenergy.data.remote.protocol.MainProtocol
import com.gahov.musenergy.data.source.articles.ArticlesLocalSource
import com.gahov.musenergy.data.source.articles.ArticlesLocalSourceImpl
import com.gahov.musenergy.data.source.articles.ArticlesRemoteSource
import com.gahov.musenergy.data.source.articles.ArticlesRemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SourceModule {

    @Provides
    @Singleton
    internal fun provideArticlesRemoteSource(
        newsProtocol: MainProtocol
    ): ArticlesRemoteSource {
        return ArticlesRemoteSourceImpl(
            protocol = newsProtocol
        )
    }

    @Provides
    @Singleton
    internal fun provideArticlesLocalSource(
        articlesDao: ArticlesDao,
        logger: Logger
    ): ArticlesLocalSource {
        return ArticlesLocalSourceImpl(
            articlesDao = articlesDao,
            logger = logger
        )
    }
}