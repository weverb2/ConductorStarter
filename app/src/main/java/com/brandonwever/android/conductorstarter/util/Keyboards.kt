package com.brandonwever.android.conductorstarter.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class Keyboards private constructor() {
    init {
        throw AssertionError("No instances.")
    }

    companion object {

        fun showKeyboard(context: Context, view: View, flags: Int = 0) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, flags)
        }

        fun showKeyboard(view: View) {
            showKeyboard(view.context, view, 0)
        }

        fun hideKeyboard(context: Context, view: View, flags: Int = 0) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, flags)
        }
    }
}