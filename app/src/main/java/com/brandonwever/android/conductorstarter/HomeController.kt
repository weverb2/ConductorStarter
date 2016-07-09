package com.brandonwever.android.conductorstarter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler

class HomeController : Controller() {

    @BindView(R.id.switch_controller_button)
    lateinit var button: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_home, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    @OnClick(R.id.switch_controller_button)
    fun onControllerSwitchClicked() {
        router.pushController(RouterTransaction.with(SecondController())
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler()))
    }
}