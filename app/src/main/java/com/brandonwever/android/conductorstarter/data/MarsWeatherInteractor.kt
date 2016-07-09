package com.brandonwever.android.conductorstarter.data

import com.brandonwever.android.conductorstarter.data.model.Report
import rx.Single
import rx.schedulers.Schedulers

class MarsWeatherInteractor(val api: MarsWeatherService) {

    fun getReports(): Single<List<Report>> {
        return api.getReportArchive().subscribeOn(Schedulers.io()).map { reportArchive -> reportArchive.results() }
    }
}