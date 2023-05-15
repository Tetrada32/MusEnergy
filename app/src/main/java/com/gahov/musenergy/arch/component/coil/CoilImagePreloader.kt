package com.gahov.musenergy.arch.component.coil

interface CoilImagePreloader {
    fun preloadSimpleResource(url: String, diskCacheKey: String)

    fun clearRecord(diskCacheKey: String): Boolean
}