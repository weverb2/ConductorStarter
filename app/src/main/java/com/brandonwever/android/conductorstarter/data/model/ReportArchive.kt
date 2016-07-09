package com.brandonwever.android.conductorstarter.data.model

import com.google.auto.value.AutoValue

@AutoValue abstract class ReportArchive {

    abstract fun count() : Int
    abstract fun next() : String
    abstract fun previous() : String
    abstract fun results() : List<Report>
}