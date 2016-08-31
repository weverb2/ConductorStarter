package com.brandonwever.android.conductorstarter.ui.util

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

class NavDrawerOwner() {

    var view: View? = null

    fun takeView(view: View) {
        this.view = view
    }

    fun dropView() {
        view = null
    }

    fun openDrawer() {
        view?.openDrawer()
    }

    fun setDrawerLockedClosed() {
        view?.setDrawerLockedClosed()
    }

    fun setDrawerUnlocked() {
        view?.setDrawerUnlocked()
    }

    interface View {
        fun openDrawer()
        fun setDrawerLockedClosed()
        fun setDrawerUnlocked()
    }

    @Module class NavDrawerModule() {
        @Provides @Singleton fun provideNavDrawerOwner(): NavDrawerOwner {
            return NavDrawerOwner()
        }
    }
}