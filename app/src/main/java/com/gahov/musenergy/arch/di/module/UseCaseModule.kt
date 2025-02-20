package com.gahov.musenergy.arch.di.module

import com.gahov.domain.repository.news.NewsRepository
import com.gahov.domain.repository.news.SearchCategoriesRepository
import com.gahov.domain.repository.stories.StoriesRepository
import com.gahov.domain.usecase.news.categories.LoadSearchCategoriesUseCase
import com.gahov.domain.usecase.news.favouties.LoadFavouritesUseCase
import com.gahov.domain.usecase.news.frontpage.LoadFrontpageUseCase
import com.gahov.domain.usecase.news.list.LoadArticleListUseCase
import com.gahov.domain.usecase.stories.LoadStoriesListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    internal fun provideLoadFrontpageUseCase(
        newsRepository: NewsRepository
    ) = LoadFrontpageUseCase(
        repository = newsRepository
    )

    @Provides
    @Singleton
    internal fun provideLoadCategoriesUseCase(
        searchCategoriesRepository: SearchCategoriesRepository
    ) = LoadSearchCategoriesUseCase(
        repository = searchCategoriesRepository
    )


    @Provides
    @Singleton
    internal fun provideLoadArticleListUseCase(
        newsRepository: NewsRepository
    ) = LoadArticleListUseCase(
        repository = newsRepository
    )

    @Provides
    @Singleton
    internal fun provideLoadStoriesListUseCase(
        storiesRepository: StoriesRepository
    ) = LoadStoriesListUseCase(
        repository = storiesRepository
    )

    @Provides
    @Singleton
    internal fun provideLoadFavouritesListUseCase(
        newsRepository: NewsRepository
    ) = LoadFavouritesUseCase(
        repository = newsRepository
    )
}