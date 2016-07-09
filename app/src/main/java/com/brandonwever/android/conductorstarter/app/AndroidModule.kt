package com.brandonwever.android.conductorstarter.app

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AndroidModule(private val application: Application) {

    @Provides
    @ForApplication
    fun provideApplicationContext(): Application {
        return application
    }
}