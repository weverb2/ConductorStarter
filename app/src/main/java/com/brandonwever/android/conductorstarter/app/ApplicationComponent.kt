package com.brandonwever.android.conductorstarter.app

import com.brandonwever.android.conductorstarter.data.DataModule
import com.brandonwever.android.conductorstarter.data.marsweather.MarsWeatherModule
import com.brandonwever.android.conductorstarter.ui.HomeController
import com.brandonwever.android.conductorstarter.ui.MainActivity
import com.brandonwever.android.conductorstarter.ui.NavDrawerOwner
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class, DataModule::class, MarsWeatherModule::class, NavDrawerOwner.NavDrawerModule::class))
interface ApplicationComponent {
    fun inject(application: App)

    fun inject(mainActivity: MainActivity)

    fun inject(homeController: HomeController)
}
