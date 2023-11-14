package com.example.windbreaker

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class WeatherData(
    val zipCode: String,
    val cityName: String,
    val temperature: Double,
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    val cloudiness: Int,
    val windSpeed: Double,
    val windDirection: Int,
    val reportTime: String,
    val rainForecast: String
) {
    companion object {
        fun convertUnixTime(unixTime: Long): String {
            val time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault())
            return time.format(Instant.ofEpochSecond(unixTime))
        }
    }
}
