package com.brandonwever.android.conductorstarter.ui

import android.support.annotation.RawRes
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

class NavDrawerOwner() {

    var navigationView: View? = null

    fun takeView(navigationView: View) {
        this.navigationView = navigationView
    }

    fun dropView() {
        navigationView = null
    }

    fun openDrawer() {
        navigationView?.openDrawer()
    }

    fun setDrawerLockedClosed() {
        navigationView?.setDrawerLockedClosed()
    }

    fun setDrawerUnlocked() {
        navigationView?.setDrawerUnlocked()
    }

    fun setCheckedMenuItem(@RawRes menuItemId: Int) {
        navigationView?.setCheckedMenuItem(menuItemId)
    }

    interface View {
        fun openDrawer()
        fun setDrawerLockedClosed()
        fun setDrawerUnlocked()
        fun setCheckedMenuItem(@RawRes menuItemId: Int)
    }

    @Module class NavDrawerModule() {
        @Provides @Singleton fun provideNavDrawerOwner(): NavDrawerOwner {
            return NavDrawerOwner()
        }
    }
}