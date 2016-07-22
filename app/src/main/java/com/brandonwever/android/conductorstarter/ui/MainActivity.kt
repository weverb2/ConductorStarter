package com.brandonwever.android.conductorstarter.ui

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.brandonwever.android.conductorstarter.R
import com.brandonwever.android.conductorstarter.app.App
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, NavDrawerOwner.View {

    lateinit var router: Router
    @Inject lateinit var navDrawerOwner: NavDrawerOwner

    @BindView(R.id.app_container) lateinit var viewGroup: ViewGroup
    @BindView(R.id.navigation_view) lateinit var navigationView: NavigationView
    @BindView(R.id.drawer_layout) lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.graph.inject(this)

        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        navigationView.setNavigationItemSelectedListener(this)
        navDrawerOwner.takeView(this)
        router = Conductor.attachRouter(this, viewGroup, savedInstanceState)

        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(HomeController()))
        }
    }

    override fun onDestroy() {
        navDrawerOwner.dropView()
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.first_controller -> router.setRoot(RouterTransaction.with(HomeController()))
            R.id.second_controller -> {
                router.setRoot(RouterTransaction.with(HomeController()))
                router.pushController(RouterTransaction.with(SecondController()))
            }
        }

        drawerLayout.closeDrawers()
        return true
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

    override fun setCheckedMenuItem(menuItemId: Int) {
        navigationView.setCheckedItem(menuItemId)
    }
}
