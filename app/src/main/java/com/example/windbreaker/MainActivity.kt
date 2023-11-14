package com.example.windbreaker

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var weatherDataList = mutableListOf<WeatherData>()
    private lateinit var zip: EditText
    private lateinit var fetchButton: Button
    private lateinit var recyclerView: RecyclerView
    private val adapter = ZipAdapter(weatherDataList)
    private val apiKey = "API KEY" // Replace with your real API key

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        zip = findViewById(R.id.zip)
        fetchButton = findViewById(R.id.fetchButton)
        recyclerView = findViewById(R.id.recyclerView)

        // Implement RecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchButton.setOnClickListener {
            val zip = zip.text.toString()
            fetchWeatherData(zip)
            fetchForecastData(zip)
        }
    }

    private fun fetchWeatherData(zipCode: String) {
        val apiURL =
            "https://api.openweathermap.org/data/2.5/weather?zip=$zipCode&appid=$apiKey&units=imperial"
        val client = AsyncHttpClient()

        client[apiURL, object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                val weatherResponse = json.jsonObject // Get JSON object

                // Extract data
                val main = weatherResponse.getJSONObject("main") // "main" object from JSON response
                val cityName = weatherResponse.getString("name")
                val cloudiness = weatherResponse.getJSONObject("clouds").getInt("all")
                val temperature = main.getDouble("temp")
                val feelsLike = main.getDouble("feels_like")
                val pressure = main.getInt("pressure")
                val humidity = main.getInt("humidity")

                val wind = weatherResponse.getJSONObject("wind")
                val windSpeed = wind.getDouble("speed")
                val windDirection = wind.getInt("deg") // degrees

                val reportTimeUnix = weatherResponse.getLong("dt") // Unix time
                val reportTimeReadable =
                    WeatherData.convertUnixTime(reportTimeUnix) // Convert time to a readable format

                val weatherData = WeatherData(
                    zipCode = zipCode,
                    cityName = cityName,
                    temperature = temperature,
                    feelsLike = feelsLike,
                    pressure = pressure,
                    humidity = humidity,
                    cloudiness = cloudiness,
                    windSpeed = windSpeed,
                    windDirection = windDirection,
                    reportTime = reportTimeReadable,
                    rainForecast = "Fetching forecast..." // Placeholder
                )

                weatherDataList.add(weatherData)
                adapter.notifyItemInserted(weatherDataList.size - 1)
                Log.d("WeatherData", "Fetched data: $weatherResponse")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.e("Weather Error", "Failed to fetch weather data")
            }
        }]
    }

    private fun fetchForecastData(zipCode: String) {
        val apiURL =
            "https://api.openweathermap.org/data/2.5/forecast?zip=$zipCode&appid=$apiKey&units=imperial"
        val client = AsyncHttpClient()

        client[apiURL, object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                val forecastResponse = json.jsonObject
                val list = forecastResponse.getJSONArray("list")

                var totalRainVolume = 0.0
                for (i in 0 until list.length()) {
                    val item = list.getJSONObject(i)
                    if (item.has("rain")) {
                        val rainObject = item.getJSONObject("rain")
                        val rainVolume = rainObject.optDouble("3h", 0.0)
                        totalRainVolume += rainVolume
                    }
                }
                val rainForecastSummary = "Total rain in next 24 hours: $totalRainVolume mm"

                // Update the WeatherData object for this ZIP code
                val weatherDataIndex = weatherDataList.indexOfFirst { it.zipCode == zipCode }
                if (weatherDataIndex != -1) {
                    val weatherData = weatherDataList[weatherDataIndex]
                    val updatedWeatherData = weatherData.copy(rainForecast = rainForecastSummary)
                    weatherDataList[weatherDataIndex] = updatedWeatherData
                    adapter.notifyItemChanged(weatherDataIndex)
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.e("Forecast Error", "Failed to fetch forecast data")
            }
        }]
    }

}