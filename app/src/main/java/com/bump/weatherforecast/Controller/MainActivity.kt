package com.bump.weatherforecast.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bump.weatherforecast.Adapter.ForecastListAdapter
import com.bump.weatherforecast.DataService.WeatherService
import com.bump.weatherforecast.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var forecastListAdapter: ForecastListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WeatherService.getNextThreeDayForecast { isSuccess ->
            if (isSuccess) {
                forecastListAdapter.notifyDataSetChanged()
            }

        }
        setupAdapter()

    }

    private fun setupAdapter() {
        this.forecastListAdapter = ForecastListAdapter(this, WeatherService.threeDayForecast)
        forecast_List.adapter = this.forecastListAdapter

        forecast_List.layoutManager = LinearLayoutManager(this)
    }
}
