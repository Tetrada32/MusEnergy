package com.gahov.musenergy.arch.di.module

import com.gahov.musenergy.data.mapper.news.ArticleRemoteMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Provides
    @Singleton
    internal fun provideArticleRemoteMapper(): ArticleRemoteMapper = ArticleRemoteMapper()
}