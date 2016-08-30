package com.brandonwever.android.conductorstarter.data

import android.app.Application
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import java.io.File
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideInitialAppState(): AppState {
        return AppState.INITIAL
    }

    @Provides
    @Singleton
    fun provideStore(appState: AppState): Store {
        return Store(appState)
    }

    @Provides
    @Singleton
    fun provideCache(app: Application): Cache {
        val cacheDir = File(app.cacheDir, "http")
        return Cache(cacheDir, DISK_CACHE_SIZE.toLong())
    }

    companion object {
        private val DISK_CACHE_SIZE = 50 * 1024 * 1024 // 50MB
    }
}
