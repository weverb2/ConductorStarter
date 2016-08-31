package com.brandonwever.android.conductorstarter.data.lcbo

import com.brandonwever.android.conductorstarter.data.lcbo.model.ProductResponse
import rx.Single
import rx.schedulers.Schedulers
import javax.inject.Inject

class LCBOInteractor @Inject constructor(val api: LCBOService) {

    fun getProducts(page: Int): Single<ProductResponse> {
        return api.getProducts(page)
                .subscribeOn(Schedulers.io())
    }
}