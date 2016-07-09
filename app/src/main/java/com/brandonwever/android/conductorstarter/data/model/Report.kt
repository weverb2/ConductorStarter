package com.brandonwever.android.conductorstarter.data.model

import com.google.auto.value.AutoValue

@AutoValue abstract class Report {

    /**
     * "terrestrial_date": "2016-07-01",
    "sol": 1387,
    "ls": 177.0,
    "min_temp": -74.0,
    "min_temp_fahrenheit": -101.2,
    "max_temp": 2.0,
    "max_temp_fahrenheit": 35.6,
    "pressure": 755.0,
    "pressure_string": "Lower",
    "abs_humidity": null,
    "wind_speed": null,
    "wind_direction": "--",
    "atmo_opacity": "Sunny",
    "season": "Month 6",
    "sunrise": "2016-07-01T10:20:00Z",
    "sunset": "2016-07-01T22:20:00Z"
     */

    abstract fun terrestrialDate() : String
    abstract fun sol() : Int
    abstract fun ls() : Double
    abstract fun minTemp() : Double
    abstract fun minTempFahrenheit() : Double
    abstract fun maxTemp() : Double
    abstract fun maxTempFahrenheit() : Double
    abstract fun pressure() : Double
    abstract fun pressureString() : String
    abstract fun atmoOpacity() : String
    abstract fun season() : String
    abstract fun sunrise() : String
    abstract fun sunset() : String

}