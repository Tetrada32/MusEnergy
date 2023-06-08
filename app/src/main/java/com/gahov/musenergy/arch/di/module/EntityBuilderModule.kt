package com.gahov.musenergy.arch.di.module

import com.gahov.musenergy.feature.articles.factory.ArticleEntityBuilder
import com.gahov.musenergy.feature.articles.factory.ArticleEntityToModelBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class EntityBuilderModule {

    @Provides
    @Reusable
    internal fun provideArticleEntityBuilder(): ArticleEntityBuilder =
        ArticleEntityToModelBuilder()
}