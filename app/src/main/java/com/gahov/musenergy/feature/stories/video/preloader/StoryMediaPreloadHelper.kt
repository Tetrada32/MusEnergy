package com.gahov.musenergy.feature.stories.video.preloader

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.upstream.DataSpec
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.HttpDataSource
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.google.android.exoplayer2.upstream.cache.CacheWriter
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import javax.inject.Inject

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