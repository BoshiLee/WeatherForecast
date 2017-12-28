package com.bump.weatherforecast.AppController

import android.app.Application
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * Created by boshili on 28/12/2017.
 */
class AppDelegate: Application() {
    companion object {
        lateinit var requestQueue: RequestQueue
    }

    override fun onCreate() {
        requestQueue = Volley.newRequestQueue(applicationContext)
        super.onCreate()
    }
}