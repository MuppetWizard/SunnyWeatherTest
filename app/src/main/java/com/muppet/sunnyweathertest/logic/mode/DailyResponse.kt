package com.muppet.sunnyweathertest.logic.mode

import com.google.gson.annotations.SerializedName
import java.util.*


data class DailyResponse(
    val result: Result,
    val status: String
)
{
    data class Result(
        val daily: Daily
    )

    data class Daily(
        val temperature: List<Temperature>,
        val skycon: List<Skycon>,
        @SerializedName("life_index")
        val lifeIndex: LifeIndex
    )

    data class Temperature(
        val max: Double,
        val min: Double
    )

    data class Skycon (
        val value: String,
        val date: Date
    )

    data class LifeIndex (
        val coldRisk: List<LifeDescription>,
        val carWashing: List<LifeDescription>,
        val ultraviolet: List<LifeDescription>,
        val dressing: List<LifeDescription>
    )

    data class LifeDescription(
        val desc: String
    )
}

