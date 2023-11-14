package com.example.windbreaker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ZipAdapter(private val weatherDataList: List<WeatherData>) :
    RecyclerView.Adapter<ZipAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cityNameTextView: TextView = view.findViewById(R.id.cityNameTextView)
        val temperatureTextView: TextView = view.findViewById(R.id.temperatureTextView)
        val feelsLikeTextView: TextView = view.findViewById(R.id.feelsLikeTextView)
        val pressureTextView: TextView = view.findViewById(R.id.pressureTextView)
        val humidityTextView: TextView = view.findViewById(R.id.humidityTextView)
        val cloudinessTextView: TextView = view.findViewById(R.id.cloudinessTextView)
        val windSpeedTextView: TextView = view.findViewById(R.id.windSpeedTextView)
        val windDirectionTextView: TextView = view.findViewById(R.id.windDirectionTextView)
        val reportTimeTextView: TextView = view.findViewById(R.id.reportTimeTextView)
        val rainForecastTextView: TextView = view.findViewById(R.id.rainForecastTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val weatherData = weatherDataList[position]

            holder.cityNameTextView.text = "City: ${weatherData.cityName}"
            holder.temperatureTextView.text = "Temperature: ${weatherData.temperature}°F"
            holder.feelsLikeTextView.text = "Feels Like: ${weatherData.feelsLike}°F"
            holder.pressureTextView.text = "Pressure: ${weatherData.pressure} hPa"
            holder.humidityTextView.text = "Humidity: ${weatherData.humidity}%"
            holder.cloudinessTextView.text = "Cloudiness: ${weatherData.cloudiness}%"
            holder.windSpeedTextView.text = "Wind Speed: ${weatherData.windSpeed} m/s"
            holder.windDirectionTextView.text = "Wind Direction: ${weatherData.windDirection}°"
            holder.reportTimeTextView.text = "Report Time: ${weatherData.reportTime}"
            holder.rainForecastTextView.text = weatherData.rainForecast
    }

    override fun getItemCount() = weatherDataList.size
}
