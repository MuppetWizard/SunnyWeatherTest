package com.muppet.sunnyweathertest.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.muppet.sunnyweathertest.logic.mode.Place
import com.muppet.sunnyweathertest.logic.network.SunnyWeatherNetWork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

object Repository {

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