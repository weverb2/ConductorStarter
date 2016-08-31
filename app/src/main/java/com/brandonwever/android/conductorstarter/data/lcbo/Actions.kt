package com.brandonwever.android.conductorstarter.data.lcbo

import com.brandonwever.android.conductorstarter.data.lcbo.model.ProductResponse
import com.brandonwever.android.conductorstarter.data.redux.Action

class LCBOProductNewestResponse(val response: ProductResponse) : Action

class LCBOProductNextResponse(val response: ProductResponse) : Action

class LCBOProductError(val error: Throwable) : Action