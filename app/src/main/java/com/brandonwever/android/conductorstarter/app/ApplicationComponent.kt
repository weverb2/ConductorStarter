package com.brandonwever.android.conductorstarter.app

import com.brandonwever.android.conductorstarter.data.DataModule
import com.brandonwever.android.conductorstarter.data.lcbo.LCBOModule
import com.brandonwever.android.conductorstarter.data.marsweather.MarsWeatherModule
import com.brandonwever.android.conductorstarter.ui.MainActivity
import com.brandonwever.android.conductorstarter.ui.login.LoginController
import com.brandonwever.android.conductorstarter.ui.productlist.ProductListController
import com.brandonwever.android.conductorstarter.ui.util.NavDrawerOwner
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class, DataModule::class, MarsWeatherModule::class, NavDrawerOwner.NavDrawerModule::class, LCBOModule::class))
interface ApplicationComponent {
    fun inject(application: App)

    fun inject(mainActivity: MainActivity)

    fun inject(loginController: LoginController)

    fun inject(productListController: ProductListController)
}
