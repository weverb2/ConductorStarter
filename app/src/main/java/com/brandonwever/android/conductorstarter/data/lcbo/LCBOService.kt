package com.brandonwever.android.conductorstarter.data.lcbo

import com.brandonwever.android.conductorstarter.data.lcbo.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Single

interface LCBOService {
    @GET("products") fun getProducts(@Query("page") page: Int): Single<ProductResponse>
}