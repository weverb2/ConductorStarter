package com.brandonwever.android.conductorstarter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bluelinelabs.conductor.Controller
import com.brandonwever.android.conductorstarter.R
import com.brandonwever.android.conductorstarter.app.App
import com.brandonwever.android.conductorstarter.data.marsweather.MarsWeatherInteractor
import rx.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

class HomeController : Controller() {

  @BindView(R.id.switch_controller_button) lateinit var button: Button

  @Inject lateinit var marsWeatherInteractor: MarsWeatherInteractor

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
    val view = inflater.inflate(R.layout.controller_home, container, false)
    ButterKnife.bind(this, view)
    App.graph.inject(this)
    return view
  }

  @OnClick(R.id.switch_controller_button)
  fun onControllerSwitchClicked() {
    marsWeatherInteractor.getReports().observeOn(AndroidSchedulers.mainThread()).subscribe(
        { reportList -> Timber.d(reportList.toString()) })
  }
}