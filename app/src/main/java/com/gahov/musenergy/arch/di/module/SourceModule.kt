package com.gahov.musenergy.arch.di.module

import com.gahov.musenergy.data.remote.protocol.MainProtocol
import com.gahov.musenergy.data.source.news.NewsRemoteSource
import com.gahov.musenergy.data.source.news.NewsRemoteSourceImpl
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
    internal fun provideNewsRemoteSource(
       newsProtocol: MainProtocol
    ): NewsRemoteSource {
        return NewsRemoteSourceImpl(
           protocol = newsProtocol
        )
    }
}