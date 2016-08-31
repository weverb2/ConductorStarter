package com.brandonwever.android.conductorstarter.data.lcbo

import com.brandonwever.android.conductorstarter.data.lcbo.model.Pager
import com.brandonwever.android.conductorstarter.data.lcbo.model.Product
import java.util.*

data class LCBOApiState(val products: List<Product> = Collections.emptyList(),
                        val pager: Pager? = null) {
}