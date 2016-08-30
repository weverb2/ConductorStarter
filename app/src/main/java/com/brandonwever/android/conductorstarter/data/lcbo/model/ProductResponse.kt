package com.brandonwever.android.conductorstarter.data.lcbo.model

import java.util.*


data class ProductResponse(var status: Int,
                           var message: Any,
                           var pager: Pager,
                           var result: List<Product> = ArrayList<Product>(),
                           var suggestion: Any) {

}

