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
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var zip: EditText
    private lateinit var fetchButton: Button
    private val apiKey = "API KEY" // Replace with your real API key
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        zip = findViewById(R.id.zip)
        fetchButton = findViewById(R.id.fetchButton)

        fetchButton.setOnClickListener {
            val zip = zip.text.toString()
            fetchWeatherData(zip)
            fetchForecastData(zip)
        }
    }

    private fun fetchWeatherData(zipCode: String) {
        val apiURL = "http://api.openweathermap.org/data/2.5/weather?zip=$zipCode&appid=$apiKey&units=imperial"
        val client = AsyncHttpClient()

        client[apiURL, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
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
                val reportTimeReadable = convertUnixTime(reportTimeUnix) // Convert time to a readable format
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
        val apiURL = "http://api.openweathermap.org/data/2.5/forecast?zip=$zipCode&appid=$apiKey&units=imperial"
        val client = AsyncHttpClient()

        client[apiURL, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                val forecastResponse = json.jsonObject
                val list = forecastResponse.getJSONArray("list")

                // List from the forecast API endpoint
                for (i in 0 until list.length()) {
                    val item = list.getJSONObject(i)
                    val dateTime = item.getString("dt_txt")

                    val rainObj = item.getJSONObject("rain")
                    val rainVolume = rainObj.optDouble("3h", 0.0) // Default to 0
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

    fun convertUnixTime(unixTime: Long): String {
        val time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault())

        return time.format(Instant.ofEpochSecond(unixTime))
    }
}