package com.gahov.musenergy.arch.di.module

import com.gahov.musenergy.data.mapper.articles.ArticlesLocalMapper
import com.gahov.musenergy.data.mapper.articles.ArticlesRemoteMapper
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
    internal fun provideArticleRemoteMapper(): ArticlesRemoteMapper = ArticlesRemoteMapper()

    @Provides
    @Singleton
    internal fun provideArticleLocalMapper(): ArticlesLocalMapper = ArticlesLocalMapper()
}