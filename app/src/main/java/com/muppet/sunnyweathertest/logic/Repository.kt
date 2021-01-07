package com.muppet.sunnyweathertest.logic

import androidx.lifecycle.liveData
import com.muppet.sunnyweathertest.logic.dao.PlaceDao
import com.muppet.sunnyweathertest.logic.mode.Place
import com.muppet.sunnyweathertest.logic.mode.Weather
import com.muppet.sunnyweathertest.logic.network.SunnyWeatherNetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

object Repository {

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

    fun refreshWeather(lng: String, lat: String,placeName: String) = fire(Dispatchers.IO){
        coroutineScope {
            val deferredRealtime = async {
                SunnyWeatherNetWork.getRealtimeWeather(lng,lat)
            }
            val deferredDaily = async {
                SunnyWeatherNetWork.getDailyWeather(lng,lat)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather = Weather(realtimeResponse.result.realtime,dailyResponse.result.daily)
                Result.success(weather)
            } else{
                Result.failure(
                    RuntimeException(
                        "realtime response status is ${realtimeResponse.status}" +
                                "daily response status is ${dailyResponse.status}"
                    )
                )
            }
        }
    }

    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = SunnyWeatherNetWork.searchPlace(query)
        if (placeResponse.status == "ok") {
            val place = placeResponse.places
            Result.success(place)
        }else{
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }

}