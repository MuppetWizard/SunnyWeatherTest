package com.muppet.sunnyweathertest.logic.network

import android.util.Log
import com.muppet.sunnyweathertest.logic.mode.DailyResponse
import com.muppet.sunnyweathertest.logic.mode.PlaceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetWork {

    private val weatherService  = ServiceCreator.create<WeatherService>()

    private val placeService = ServiceCreator.create<PlaceService>()

    suspend fun searchPlace(query: String) : PlaceResponse {
        Log.e("999", "searchPlace: " + query)
        return placeService.searchPlace(query).await()
    }

    suspend fun getDailyWeather(lng: String, lat: String) : DailyResponse {
        return weatherService.getDailyWeather(lng,lat).await()
    }

    suspend fun getRealtimeWeather(lng: String, lat: String) =
        weatherService.getRealtimeWeather(lng,lat).await()

    private suspend fun <T> Call<T>.await(): T{
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    body?.let { continuation.resume(body) }
                        ?: continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}