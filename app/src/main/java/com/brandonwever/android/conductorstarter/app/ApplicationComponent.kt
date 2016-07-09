package com.brandonwever.android.conductorstarter.app

import com.brandonwever.android.conductorstarter.HomeController
import com.brandonwever.android.conductorstarter.MainActivity
import com.brandonwever.android.conductorstarter.data.MarsWeatherModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class, MarsWeatherModule::class))
interface ApplicationComponent {
    fun inject(application: App)

    fun inject(mainActivity: MainActivity)

    fun inject(homeController: HomeController)
}
