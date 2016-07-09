package com.brandonwever.android.conductorstarter.data.model

import auto.parcelgson.AutoParcelGson

@AutoParcelGson abstract class Report {
  abstract fun terrestrialDate(): String
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
  abstract fun sunrise(): String
  abstract fun sunset(): String
}