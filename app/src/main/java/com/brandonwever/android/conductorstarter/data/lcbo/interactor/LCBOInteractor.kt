package com.brandonwever.android.conductorstarter.data.lcbo.interactor

import com.brandonwever.android.conductorstarter.data.lcbo.model.ProductResponse
import rx.Single

interface LCBOInteractor {
    fun getProducts(page: Int): Single<ProductResponse>
}