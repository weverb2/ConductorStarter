package com.brandonwever.android.conductorstarter.ui

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.ViewGroup
import com.bluelinelabs.conductor.*
import com.brandonwever.android.conductorstarter.R
import com.brandonwever.android.conductorstarter.app.App
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, NavDrawerOwner.View, ControllerChangeHandler.ControllerChangeListener {

    lateinit var router: Router
    @Inject lateinit var navDrawerOwner: NavDrawerOwner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.graph.inject(this)

        setContentView(R.layout.activity_main)

        navigationView.setNavigationItemSelectedListener(this)
        navDrawerOwner.takeView(this)
        router = Conductor.attachRouter(this, appContainer, savedInstanceState)
        router.addChangeListener(this)

        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(HomeController()))
        }
    }

    override fun onDestroy() {
        navDrawerOwner.dropView()
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawers()
            return
        }
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (!item.isChecked) {
            when (item.itemId) {
                R.id.first_controller -> router.popToRoot()
                R.id.second_controller -> menuNavigateToController(SecondController())
            }
        }
        drawerLayout.closeDrawers()
        return true
    }

    private fun menuNavigateToController(controller: Controller) {
        router.popToRoot()
        router.pushController(RouterTransaction.with(controller))
    }

    override fun openDrawer() {
        drawerLayout.openDrawer(navigationView)
    }

    override fun setDrawerLockedClosed() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun setDrawerUnlocked() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    override fun onChangeCompleted(to: Controller?, from: Controller?, isPush: Boolean, container: ViewGroup?, handler: ControllerChangeHandler?) {
        when (to) {
            is HomeController -> navigationView.setCheckedItem(R.id.first_controller)
            is SecondController -> navigationView.setCheckedItem(R.id.second_controller)
        }
    }

    override fun onChangeStarted(to: Controller?, from: Controller?, isPush: Boolean, container: ViewGroup?, handler: ControllerChangeHandler?) {
    }

}
