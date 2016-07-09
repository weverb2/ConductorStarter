package com.brandonwever.android.conductorstarter.data

import com.brandonwever.android.conductorstarter.data.model.Report
import rx.Single
import rx.schedulers.Schedulers
import javax.inject.Inject

class MarsWeatherInteractor @Inject constructor(val api: MarsWeatherService) {

  fun getReports(): Single<List<Report>> {
    return api.getReportArchive().subscribeOn(
        Schedulers.io()).map { reportArchive -> reportArchive.results() }
  }
}