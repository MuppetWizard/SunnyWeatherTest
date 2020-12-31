package com.muppet.sunnyweathertest.logic.network

import android.util.Log
import com.muppet.sunnyweathertest.logic.mode.PlaceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetWork {

    private val weatherService  = ""

    private val placeService = ServiceCreator.create<PlaceService>()

    suspend fun searchPlace(query: String) : PlaceResponse {
        Log.e("999", "searchPlace: " + query)
        return placeService.searchPlace(query).await()
    }

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