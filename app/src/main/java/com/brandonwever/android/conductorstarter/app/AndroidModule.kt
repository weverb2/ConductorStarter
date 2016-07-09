package com.brandonwever.android.conductorstarter.app

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule(private val application: Application) {

  @Provides
  @Singleton
  fun provideApplicationContext(): Application {
    return application
  }
}