package com.gahov.musenergy.feature.stories.video.preloader

import android.content.Context
import android.net.Uri
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.HttpDataSource
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.CacheWriter
import androidx.media3.datasource.cache.SimpleCache

import javax.inject.Inject

@OptIn(UnstableApi::class)
class StoryMediaPreloadHelper @Inject constructor(
    private var context: Context,
    private var simpleCacheProvider: SimpleCache
) : PreloadHelper {
    private lateinit var mHttpDataSourceFactory: HttpDataSource.Factory
    private lateinit var mDefaultDataSourceFactory: DefaultDataSource.Factory
    private lateinit var mCacheDataSource: CacheDataSource

    override suspend fun preloadVideo(url: String) {
        doPreloadingWork(videoUrl = url)
    }

    private fun doPreloadingWork(videoUrl: String?) {
        try {
            mHttpDataSourceFactory = DefaultHttpDataSource.Factory()
                .setAllowCrossProtocolRedirects(true)

            mDefaultDataSourceFactory = DefaultDataSource.Factory(context, mHttpDataSourceFactory)

            mCacheDataSource = CacheDataSource.Factory()
                .setCache(simpleCacheProvider)
                .setUpstreamDataSourceFactory(mHttpDataSourceFactory)
                .createDataSource()
            preCacheVideo(videoUrl)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun preCacheVideo(videoUrl: String?) {
        val videoUri = Uri.parse(videoUrl)
        val dataSpec = DataSpec(videoUri)
        cacheVideo(dataSpec)
    }

    private fun cacheVideo(
        mDataSpec: DataSpec
    ) {
        runCatching {
            CacheWriter(mCacheDataSource, mDataSpec, null, null).cache()
        }.onFailure {
            it.printStackTrace()
        }
    }
}