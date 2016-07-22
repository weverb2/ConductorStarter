package com.brandonwever.android.conductorstarter.ui

import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.brandonwever.android.conductorstarter.R
import com.brandonwever.android.conductorstarter.app.App
import com.brandonwever.android.conductorstarter.data.marsweather.MarsWeatherInteractor
import rx.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

class HomeController : Controller(), View.OnClickListener {

    @Inject lateinit var marsWeatherInteractor: MarsWeatherInteractor
    @Inject lateinit var navDrawerOwner: NavDrawerOwner

    @BindView(R.id.switch_controller_button) lateinit var button: Button
    @BindView(R.id.main_tool_bar) lateinit var toolbar: Toolbar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        App.graph.inject(this)
        val view = inflater.inflate(R.layout.controller_home, container, false)
        ButterKnife.bind(this, view)
        initToolbar()
        return view
    }

    private fun initToolbar() {
        toolbar.setTitleTextColor(ContextCompat.getColor(applicationContext, android.R.color.white))
        toolbar.title = "Home Controller"
        toolbar.setNavigationIcon(R.drawable.ic_menu)
        toolbar.setNavigationOnClickListener(this)
    }

    @OnClick(R.id.switch_controller_button)
    fun onControllerSwitchClicked() {
        marsWeatherInteractor.getReports()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ reportList -> Timber.d(reportList.toString()) })
        router.pushController(RouterTransaction.with(SecondController()))
    }

    override fun onClick(v: View?) {
        navDrawerOwner.openDrawer()
    }
}