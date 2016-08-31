package com.brandonwever.android.conductorstarter.data.lcbo

import com.brandonwever.android.conductorstarter.data.Action
import com.brandonwever.android.conductorstarter.data.lcbo.model.ProductResponse

class LCBOProductNewestResponse(val response: ProductResponse) : Action

class LCBOProductNextResponse(val response: ProductResponse) : Action

class LCBOProductError(val error: Throwable) : Action