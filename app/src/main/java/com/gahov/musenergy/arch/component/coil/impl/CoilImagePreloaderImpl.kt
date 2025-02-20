package com.gahov.musenergy.arch.component.coil.impl

import android.content.Context
import com.gahov.musenergy.arch.component.coil.CoilImagePreloader
import coil3.ImageLoader
import coil3.imageLoader
import coil3.request.CachePolicy
import coil3.request.ImageRequest

class CoilImagePreloaderImpl(private val context: Context) : CoilImagePreloader {

    override fun preloadSimpleResource(url: String, diskCacheKey: String) {
        val imageLoader: ImageLoader = context.imageLoader
        val request = ImageRequest.Builder(context)
            .data(url)
            .diskCacheKey(diskCacheKey)
            .memoryCachePolicy(CachePolicy.DISABLED)
            .diskCachePolicy(CachePolicy.WRITE_ONLY)
            .build()
        imageLoader.enqueue(request)
    }

    override fun clearRecord(diskCacheKey: String): Boolean {
        return try {
            val loader = context.imageLoader
            loader.diskCache?.remove(diskCacheKey) ?: false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}