package com.bump.weatherforecast.Adapter

import android.content.Context
import com.bump.weatherforecast.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bump.weatherforecast.Model.Weather

/**
 * Created by boshili on 27/12/2017.
 */
class ForecastListAdapter(val context: Context, val weathers: List<Weather>)
    : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(weathers[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.weatherforcast_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = this.weathers.count()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val date = itemView?.findViewById<TextView>(R.id.weekTimeTextView)
        private val minTemp = itemView?.findViewById<TextView>(R.id.tempMinTextView)
        private val maxTemp = itemView?.findViewById<TextView>(R.id.tempMaxTextView)
        private val humidity = itemView?.findViewById<TextView>(R.id.humidityTextView)
        private val desc = itemView?.findViewById<TextView>(R.id.descriptionTextView)

        fun bind(weather: Weather) {
            date?.text = weather.date
            minTemp?.text = weather.minTemp.toString()
            maxTemp?.text = weather.maxTemp.toString()
            humidity?.text = weather.humidity.toString()
            desc?.text = weather.description
        }
    }
}