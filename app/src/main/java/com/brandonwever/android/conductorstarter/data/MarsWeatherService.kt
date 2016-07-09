package com.brandonwever.android.conductorstarter.data

import com.brandonwever.android.conductorstarter.data.model.ReportArchive
import retrofit2.http.GET
import rx.Single

interface MarsWeatherService {
    @GET("v1/archive") fun getReportArchive(): Single<ReportArchive>
}