package com.brandonwever.android.conductorstarter.data.lcbo

import com.brandonwever.android.conductorstarter.data.lcbo.model.Pager
import com.brandonwever.android.conductorstarter.data.lcbo.model.Product
import java.util.*

data class LCBOApiState(val products: List<Product>,
                        val pager: Pager?) {

    companion object {
        val INITIAL = LCBOApiState(Collections.emptyList(), null)
    }
}