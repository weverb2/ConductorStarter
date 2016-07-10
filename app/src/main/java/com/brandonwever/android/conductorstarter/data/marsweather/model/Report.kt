package com.brandonwever.android.conductorstarter.data.marsweather.model

import auto.parcelgson.AutoParcelGson
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime

@AutoParcelGson abstract class Report {
  abstract fun terrestrialDate(): LocalDate
  abstract fun sol(): Int
  abstract fun ls(): Double
  abstract fun minTemp(): Double
  abstract fun minTempFahrenheit(): Double
  abstract fun maxTemp(): Double
  abstract fun maxTempFahrenheit(): Double
  abstract fun pressure(): Double
  abstract fun pressureString(): String
  abstract fun atmoOpacity(): String
  abstract fun season(): String
  abstract fun sunrise(): ZonedDateTime
  abstract fun sunset(): ZonedDateTime
}