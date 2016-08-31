package com.brandonwever.android.conductorstarter.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*

class ProductListingView : LinearLayout {

    lateinit var productName: TextView
    lateinit var productUnitType: TextView
    lateinit var productImage: ImageView

    private fun init() = AnkoContext.createDelegate(this).apply {
        orientation = LinearLayout.HORIZONTAL
        layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent)
        productImage = imageView {
            layoutParams = LinearLayout.LayoutParams(dip(48), dip(48))
        }
        verticalLayout() {
            productName = textView {
                textSize = 16f
            }
            productUnitType = textView {
                textSize = 16f
            }
        }
    }

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }
}