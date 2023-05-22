package com.gahov.musenergy.arch.di.module

import com.gahov.domain.repository.news.NewsRepository
import com.gahov.domain.usecase.news.frontpage.LoadFrontpageUseCase
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
}