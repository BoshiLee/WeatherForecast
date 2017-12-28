package com.bump.weatherforecast.DataService

import com.bump.weatherforecast.Model.Weather

/**
 * Created by boshili on 2017-12-28.
 */
object WeatherService {

    var threeDayForecast = mutableListOf<Weather>(
            Weather("Wed", 22, 32, 60, "scattered clouds"),
            Weather("Thu", 20, 30, 80, "light rain"),
            Weather("Fri", 18, 25,90, "light rain")
    )
}