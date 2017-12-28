package com.bump.weatherforecast.DataService

import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.bump.weatherforecast.AppController.AppDelegate
import com.bump.weatherforecast.Const.JSON_CONTENT_TYPE
import com.bump.weatherforecast.Const.URL_WEATHER
import com.bump.weatherforecast.Model.Weather
import org.json.JSONException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by boshili on 2017-12-28.
 */
object WeatherService {

    val threeDayForecast = ArrayList<Weather>()

    private fun errorListener(message: String, complection: (Boolean) -> Unit) = Response.ErrorListener { error ->
        Log.d("Error", "$message ${error.localizedMessage}")
        complection(false)
    }

    fun tryCatchBlock(complete: (Boolean) -> Unit, tryBlock: () -> Unit) {
        try {
            tryBlock()
            complete(true)
        } catch (e: JSONException) {
            Log.d("JSON", "EXC: ${e.localizedMessage}")
            complete(false)
        }
    }


    fun getNextThreeDayForecast(complection: (Boolean) -> Unit) {
        val request = object : JsonObjectRequest(Method.GET, URL_WEATHER, null, Response.Listener { response ->
            tryCatchBlock(complection) {

                val weatherList = response.getJSONArray("list")
                for (ix in 0 until weatherList.length()) {

                    val minTemp: Int = weatherList.getJSONObject(ix).getJSONObject("main").getInt("temp_min")
                    val maxTemp: Int = weatherList.getJSONObject(ix).getJSONObject("main").getInt("temp_max")
                    val humidity: Int = weatherList.getJSONObject(ix).getJSONObject("main").getInt("humidity")
                    val date: String = weatherList.getJSONObject(ix).getString("dt_txt")
                    val description: String = weatherList.getJSONObject(ix).getJSONArray("weather").getJSONObject(0).getString("description")
                    this.threeDayForecast.add(Weather(date, minTemp, maxTemp, humidity, description))
                }

            }

        }, errorListener("Can't fethch weather json", complection)) {
            override fun getBodyContentType(): String = JSON_CONTENT_TYPE
        }

        AppDelegate.requestQueue.add(request)
    }


}

object WeatherUtils {

    fun returnCelsius(kelvin: Int): Int = (kelvin - 273.15).toInt()

    fun returnDataString(dateString: String) : String {


        val regularFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        regularFormatter.timeZone = TimeZone.getTimeZone("UTC")
        var convertedDate = Date()

        try {
            convertedDate = regularFormatter.parse(dateString)
        } catch (e: ParseException) {
            Log.d("EXC", "Can't parse data")
        }
        val outDateFormatter = SimpleDateFormat("MM 月 dd E a h:mm", Locale.getDefault())

        return outDateFormatter.format(convertedDate)
    }

}