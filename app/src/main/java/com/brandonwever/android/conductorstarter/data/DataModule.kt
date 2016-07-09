package com.brandonwever.android.conductorstarter.data

import android.app.Application
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideCache(app: Application): Cache {
        val cacheDir = File(app.cacheDir, "http")
        return Cache(cacheDir, DISK_CACHE_SIZE.toLong())
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        return OkHttpClient.Builder().cache(cache).build()
    }

    companion object {
        private val DISK_CACHE_SIZE = 50 * 1024 * 1024 // 50MB
    }
}
