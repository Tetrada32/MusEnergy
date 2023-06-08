package com.gahov.musenergy.arch.di.module

import android.content.Context
import com.gahov.musenergy.feature.stories.video.player.StoriesPlayerManager
import com.gahov.musenergy.feature.stories.video.preloader.PreloadHelper
import com.gahov.musenergy.feature.stories.video.preloader.StoryMediaPreloadHelper
import com.google.android.exoplayer2.database.StandaloneDatabaseProvider
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ExoModule {

    @Singleton
    @Provides
    fun provideSimpleCache(
        context: Context,
        evictor: LeastRecentlyUsedCacheEvictor,
        databaseProvider: StandaloneDatabaseProvider
    ): SimpleCache {
        return SimpleCache(context.cacheDir, evictor, databaseProvider)
    }

    @Singleton
    @Provides
    fun provideStandaloneDatabaseProvider(context: Context): StandaloneDatabaseProvider {
        return StandaloneDatabaseProvider(context)
    }

    @Reusable
    @Provides
    fun provideLeastRecentlyUsedCacheEvictor(): LeastRecentlyUsedCacheEvictor {
        return LeastRecentlyUsedCacheEvictor(MAX_CACHE_BYTES)
    }

    @Reusable
    @Provides
    fun provideVideoPreloadHelper(
        context: Context,
        simpleCache: SimpleCache
    ): PreloadHelper {
        return StoryMediaPreloadHelper(context = context, simpleCacheProvider = simpleCache)
    }

    @Provides
    @Singleton
    fun provideStoriesPlayerManager(
        context: Context,
        simpleCache: SimpleCache
    ): StoriesPlayerManager {
        return StoriesPlayerManager(context, simpleCache)
    }

    companion object {
        private const val MAX_CACHE_BYTES: Long = 90 * 1024 * 1024
    }
}