package com.gahov.musenergy.arch.di.module

import com.gahov.domain.repository.news.NewsRepository
import com.gahov.domain.repository.news.SearchCategoriesRepository
import com.gahov.domain.repository.stories.StoriesRepository
import com.gahov.musenergy.data.mapper.news.ArticleRemoteMapper
import com.gahov.musenergy.data.repository.news.NewsRepositoryImpl
import com.gahov.musenergy.data.repository.categories.SearchCategoriesRepositoryImpl
import com.gahov.musenergy.data.repository.stories.StoriesRepositoryImpl
import com.gahov.musenergy.data.source.news.NewsRemoteSource
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
    internal fun provideNewsRepository(
        newsRemoteSource: NewsRemoteSource,
        articleRemoteMapper: ArticleRemoteMapper
    ): NewsRepository {
        return NewsRepositoryImpl(
            remoteSource = newsRemoteSource,
            articleRemoteMapper = articleRemoteMapper
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
}