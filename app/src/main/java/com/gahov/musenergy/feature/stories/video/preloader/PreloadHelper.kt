package com.gahov.musenergy.feature.stories.video.preloader

interface PreloadHelper {

    suspend fun preloadVideo(url: String)
}