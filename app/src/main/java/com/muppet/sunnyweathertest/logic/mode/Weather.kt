package com.muppet.sunnyweathertest.logic.mode

data class Weather(
    val realtime: RealTimeResponse.Realtime,
    val daily: DailyResponse.Daily
)