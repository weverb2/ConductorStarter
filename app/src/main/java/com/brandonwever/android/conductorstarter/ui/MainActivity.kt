package com.brandonwever.android.conductorstarter.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.brandonwever.android.conductorstarter.R.id
import com.brandonwever.android.conductorstarter.R.layout

class MainActivity : AppCompatActivity() {

  lateinit var router: Router

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)

    val viewGroup = findViewById(id.app_container) as ViewGroup
    router = Conductor.attachRouter(this, viewGroup, savedInstanceState)
    if (!router.hasRootController()) {
      router.setRoot(RouterTransaction.with(HomeController()))
    }
  }

  override fun onBackPressed() {
    if (!router.handleBack()) {
      super.onBackPressed()
    }
  }
}
