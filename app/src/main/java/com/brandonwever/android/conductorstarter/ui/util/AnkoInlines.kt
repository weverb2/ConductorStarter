package com.brandonwever.android.conductorstarter.ui.util

import android.view.ViewManager
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.changeHandlerFrameLayout(theme: Int = 0) = changeHandlerFrameLayout(theme) {}
inline fun ViewManager.changeHandlerFrameLayout(theme: Int = 0, init: ChangeHandlerFrameLayout.() -> Unit) = ankoView({ ChangeHandlerFrameLayout(it) }, theme, init)