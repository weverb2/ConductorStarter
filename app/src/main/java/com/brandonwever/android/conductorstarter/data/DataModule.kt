package com.brandonwever.android.conductorstarter.data

import android.app.Application
import com.brandonwever.android.conductorstarter.data.lcbo.LCBOReducer
import com.brandonwever.android.conductorstarter.data.redux.*
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
    fun provideAppReducer(): Reducer<AppState, Action> {
        return CombineReducers(LCBOReducer())
    }

    @Provides
    @Singleton
    fun provideStore(appState: AppState, reducer: Reducer<AppState, Action>): RxStore<AppState, Action> {
        return RxStore(appState, reducer)
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
