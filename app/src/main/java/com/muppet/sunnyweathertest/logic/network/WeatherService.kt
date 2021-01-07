package com.muppet.sunnyweathertest.logic.network

import com.muppet.sunnyweathertest.MyApplication
import com.muppet.sunnyweathertest.logic.mode.DailyResponse
import com.muppet.sunnyweathertest.logic.mode.RealTimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    @GET("v2.5/${MyApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<RealTimeResponse>

    @GET("v2.5/${MyApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>

}