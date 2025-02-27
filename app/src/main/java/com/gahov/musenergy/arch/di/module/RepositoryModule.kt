package com.gahov.musenergy.arch.di.module

import com.gahov.domain.component.logger.Logger
import com.gahov.domain.repository.articles.ArticlesRepository
import com.gahov.domain.repository.articles.SearchCategoriesRepository
import com.gahov.domain.repository.favorites.FavoritesRepository
import com.gahov.domain.repository.stories.StoriesRepository
import com.gahov.musenergy.data.mapper.articles.ArticlesLocalMapper
import com.gahov.musenergy.data.mapper.articles.ArticlesRemoteMapper
import com.gahov.musenergy.data.repository.articles.ArticlesRepositoryImpl
import com.gahov.musenergy.data.repository.categories.SearchCategoriesRepositoryImpl
import com.gahov.musenergy.data.repository.favorites.FavoritesRepositoryImpl
import com.gahov.musenergy.data.repository.stories.StoriesRepositoryImpl
import com.gahov.musenergy.data.source.articles.ArticlesLocalSource
import com.gahov.musenergy.data.source.articles.ArticlesRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    internal fun provideArticlesRepository(
        articlesRemoteSource: ArticlesRemoteSource,
        articlesRemoteMapper: ArticlesRemoteMapper,
        articlesLocalSource: ArticlesLocalSource,
        articlesLocalMapper: ArticlesLocalMapper
    ): ArticlesRepository {
        return ArticlesRepositoryImpl(
            remoteSource = articlesRemoteSource,
            localSource = articlesLocalSource,
            remoteMapper = articlesRemoteMapper,
            localMapper = articlesLocalMapper
        )
    }

    @Provides
    @Singleton
    internal fun provideSearchCategoriesRepository(): SearchCategoriesRepository {
        return SearchCategoriesRepositoryImpl()
    }

    @Provides
    @Singleton
    internal fun provideStoriesRepository(): StoriesRepository {
        return StoriesRepositoryImpl()
    }

    @Provides
    @Singleton
    internal fun provideFavoritesRepository(
        articlesLocalSource: ArticlesLocalSource,
        articlesLocalMapper: ArticlesLocalMapper,
        logger: Logger
    ): FavoritesRepository {
        return FavoritesRepositoryImpl(
            localSource = articlesLocalSource,
            localMapper = articlesLocalMapper,
            logger = logger
        )
    }
}