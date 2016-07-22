package com.brandonwever.android.conductorstarter.data.marsweather.model

import auto.parcelgson.AutoParcelGson

@AutoParcelGson abstract class ReportArchive {
    abstract fun count(): Int
    abstract fun next(): String
    abstract fun previous(): String
    abstract fun results(): List<Report>
}