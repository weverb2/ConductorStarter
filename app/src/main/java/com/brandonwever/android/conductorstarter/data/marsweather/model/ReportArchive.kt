package com.brandonwever.android.conductorstarter.data.marsweather.model

data class ReportArchive(val count: Int,
                         val next: String,
                         val previous: String,
                         val results: List<Report>)
