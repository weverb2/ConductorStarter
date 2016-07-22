package com.brandonwever.android.conductorstarter.ui

import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.bluelinelabs.conductor.Controller
import com.brandonwever.android.conductorstarter.R
import com.brandonwever.android.conductorstarter.app.App
import javax.inject.Inject

class SecondController : Controller(), View.OnClickListener {

    @Inject lateinit var navDrawerOwner: NavDrawerOwner

    @BindView(R.id.main_tool_bar) lateinit var toolbar: Toolbar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        App.graph.inject(this)
        val view = inflater.inflate(R.layout.controller_second, container, false)
        ButterKnife.bind(this, view)
        initToolbar()
        return view
    }

    private fun initToolbar() {
        toolbar.setTitleTextColor(ContextCompat.getColor(applicationContext, android.R.color.white))
        toolbar.title = "Second Controller"
        toolbar.setNavigationIcon(R.drawable.ic_menu)
        toolbar.setNavigationOnClickListener(this)
    }

    override fun onClick(v: View?) {
        navDrawerOwner.openDrawer()
    }
}