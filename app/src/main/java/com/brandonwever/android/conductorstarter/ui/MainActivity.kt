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
import com.brandonwever.android.conductorstarter.ui.util.changeHandlerFrameLayout
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.support.v4.drawerLayout
import org.jetbrains.anko.wrapContent
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, NavDrawerOwner.View, ControllerChangeHandler.ControllerChangeListener {

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

//    <com.bluelinelabs.conductor.ChangeHandlerFrameLayout
//    android:id="@+id/app_container"
//    android:layout_height="match_parent"
//    android:layout_width="match_parent"
//    tools:context=".ui.MainActivity">
//    </com.bluelinelabs.conductor.ChangeHandlerFrameLayout>
//    <android.support.design.widget.NavigationView
//    android:id="@+id/navigation_view"
//    android:layout_height="match_parent"
//    android:layout_width="wrap_content"
//    app:menu="@menu/navigation_menu"
//    android:fitsSystemWindows="true"
//    android:layout_gravity="start"/>
//    </android.support.v4.widget.DrawerLayout>


    class MainActivityView : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            drawerLayout {
                layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
                fitsSystemWindows = true

                changeHandlerFrameLayout {
                    layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
                }
                navigationView {
                    layoutParams = ViewGroup.LayoutParams(wrapContent, matchParent)
                }
            }
        }

    }
}
