package com.brandonwever.android.conductorstarter.app

import com.brandonwever.android.conductorstarter.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class))
interface ApplicationComponent {
    fun inject(application: App)

    fun inject(mainActivity: MainActivity)
}
