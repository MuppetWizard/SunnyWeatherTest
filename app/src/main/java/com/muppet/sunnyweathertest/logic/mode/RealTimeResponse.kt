package com.muppet.sunnyweathertest.logic.mode

import com.google.gson.annotations.SerializedName

data class RealTimeResponse(
    val result: Result,
    val status: String
)
{
    data class Result(
        val realtime: Realtime
    )

    data class Realtime(
        @SerializedName("air_quality")
        val airQuality: AirQuality,
        val skycon: String,
        val temperature: Double
    )

    data class AirQuality(
        val aqi: Aqi
    )

    data class Aqi(
        val chn: Double
    )
}

