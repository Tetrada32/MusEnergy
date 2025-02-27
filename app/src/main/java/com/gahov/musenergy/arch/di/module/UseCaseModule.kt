package com.gahov.musenergy.arch.di.module

import com.gahov.domain.repository.articles.ArticlesRepository
import com.gahov.domain.repository.articles.SearchCategoriesRepository
import com.gahov.domain.repository.favorites.FavoritesRepository
import com.gahov.domain.repository.stories.StoriesRepository
import com.gahov.domain.usecase.articles.categories.LoadSearchCategoriesUseCase
import com.gahov.domain.usecase.articles.details.FetchArticleDetailsUseCase
import com.gahov.domain.usecase.articles.favorites.FetchFavouritesUseCase
import com.gahov.domain.usecase.articles.frontpage.LoadFrontpageUseCase
import com.gahov.domain.usecase.articles.frontpage.UpdateFrontpageUseCase
import com.gahov.domain.usecase.articles.list.LoadArticleListUseCase
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
        articlesRepository: ArticlesRepository
    ) = LoadFrontpageUseCase(repository = articlesRepository)

    @Provides
    @Singleton
    internal fun provideLoadCategoriesUseCase(
        searchCategoriesRepository: SearchCategoriesRepository
    ) = LoadSearchCategoriesUseCase(repository = searchCategoriesRepository)


    @Provides
    @Singleton
    internal fun provideLoadArticleListUseCase(
        articlesRepository: ArticlesRepository
    ) = LoadArticleListUseCase(repository = articlesRepository)

    @Provides
    @Singleton
    internal fun provideLoadStoriesListUseCase(
        storiesRepository: StoriesRepository
    ) = LoadStoriesListUseCase(repository = storiesRepository)

    @Provides
    @Singleton
    internal fun provideFetchFavouritesListUseCase(
        favoritesRepository: FavoritesRepository
    ) = FetchFavouritesUseCase(repository = favoritesRepository)

    @Provides
    @Singleton
    internal fun provideUpdateFrontpageUseCase(
        articlesRepository: ArticlesRepository
    ) = UpdateFrontpageUseCase(repository = articlesRepository)

    @Provides
    @Singleton
    internal fun provideFetchArticleDetailsUseCase(
        articlesRepository: ArticlesRepository
    ) = FetchArticleDetailsUseCase(repository = articlesRepository)
}