package com.brandonwever.android.conductorstarter.data.lcbo.model

import java.util.*


data class ProductResponse(var status: Int = 0,
                           var message: String? = null,
                           var pager: Pager = Pager(),
                           var result: List<Product> = ArrayList<Product>(),
                           var suggestion: String? = null) {

}

