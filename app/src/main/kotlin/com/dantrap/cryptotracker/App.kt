package com.dantrap.cryptotracker

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import com.dantrap.cryptotracker.di.initKoin

class App : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    override fun newImageLoader(): ImageLoader = ImageLoader.Builder(this)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .memoryCache {
            MemoryCache.Builder(this)
                .maxSizePercent(0.25)
                .build()
        }
        .diskCachePolicy(CachePolicy.ENABLED)
        .diskCache {
            DiskCache.Builder()
                .directory(cacheDir)
                .maxSizePercent(0.02)
                .build()
        }
        .respectCacheHeaders(false)
        .build()
}
