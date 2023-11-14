package com.example.windbreaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import android.util.Log

class MainActivity : AppCompatActivity() {

    private lateinit var zip: EditText
    private lateinit var fetchButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        zip = findViewById(R.id.zip)
        fetchButton = findViewById(R.id.fetchButton)

        fetchButton.setOnClickListener {
            val zip = zip.text.toString()
            fetchWeatherData(zip)
        }
    }

    private fun fetchWeatherData(zipCode: String) {
        var apiKey = "API KEY"
        val apiURL = "http://api.openweathermap.org/data/2.5/weather?zip=$zipCode&appid=$apiKey&units=imperial"
        val client = AsyncHttpClient()

        client[apiURL, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                val jsonResponse = json.jsonObject // Get JSON object

                // Extract data
                val main = jsonResponse.getJSONObject("main") // "main" object from JSOn response
                val temperature = main.getDouble("temp")
                val feelsLike = main.getDouble("feels_like")
                val pressure = main.getInt("pressure")
                val humidity = main.getInt("humidity")

                val wind = jsonResponse.getJSONObject("wind")
                val windSpeed = wind.getDouble("speed")
                val windDirection = wind.getInt("deg") // degrees
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
}