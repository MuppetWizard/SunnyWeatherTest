package com.muppet.sunnyweathertest.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.muppet.sunnyweathertest.logic.mode.Place
import com.muppet.sunnyweathertest.logic.network.SunnyWeatherNetWork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

object Repository {

    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetWork.searchPlace(query)
            if (placeResponse.status == "ok") {
                val place = placeResponse.place
                Result.success(place)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}