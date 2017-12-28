package com.bump.weatherforecast.Const

/**
 * Created by boshili on 2017-12-28.
 */
const val CITY = "Tainan"
const val BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?q=${CITY},tw&mode=json&appid="
const val API_KEY = "3fb4bd443ed74ca3d151a34c3bddb03c" // 請自行申請更換
const val URL_WEATHER = "${BASE_URL}${API_KEY}"

const val JSON_CONTENT_TYPE = "application/json; charset=utf-8"