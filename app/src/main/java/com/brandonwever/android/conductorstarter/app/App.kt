package com.brandonwever.android.conductorstarter.app

import android.app.Application
import com.brandonwever.android.conductorstarter.BuildConfig
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class App : Application() {

  companion object {
    //platformStatic allow access it from java code
    @JvmStatic lateinit var graph: ApplicationComponent
  }

  override fun onCreate() {
    super.onCreate()
    LeakCanary.install(this);
    graph = DaggerApplicationComponent.builder().androidModule(AndroidModule(this)).build()
    graph.inject(this)
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree());
    }
  }
}