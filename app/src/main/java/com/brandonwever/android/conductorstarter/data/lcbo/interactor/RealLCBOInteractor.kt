package com.brandonwever.android.conductorstarter.data.lcbo.interactor

import com.brandonwever.android.conductorstarter.data.lcbo.LCBOService
import com.brandonwever.android.conductorstarter.data.lcbo.model.ProductResponse
import rx.Single
import rx.schedulers.Schedulers
import javax.inject.Inject

class RealLCBOInteractor @Inject constructor(val api: LCBOService) : LCBOInteractor {
    override fun getProducts(page: Int): Single<ProductResponse> {
        return api.getProducts(page)
                .subscribeOn(Schedulers.io())
    }
}