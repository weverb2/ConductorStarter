package com.brandonwever.android.conductorstarter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.brandonwever.android.conductorstarter.R

class SecondController : Controller() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_second, container, false)

        return view
    }
}