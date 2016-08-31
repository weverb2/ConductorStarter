package com.brandonwever.android.conductorstarter.data.marsweather.model

import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime

data class Report(val terrestrialDate: LocalDate,
                  val sol: Int,
                  val ls: Double,
                  val minTemp: Double,
                  val minTempFahrenheit: Double,
                  val maxTemp: Double,
                  val maxTempFahrenheit: Double,
                  val pressure: Double,
                  val pressureString: String,
                  val atmoOpacity: String,
                  val season: String,
                  val sunrise: ZonedDateTime,
                  val sunset: ZonedDateTime)
