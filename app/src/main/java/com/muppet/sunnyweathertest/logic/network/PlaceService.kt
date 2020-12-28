package com.muppet.sunnyweathertest.logic.network

import android.telecom.Call
import com.muppet.sunnyweathertest.Application
import com.muppet.sunnyweathertest.logic.mode.PlaceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
    @GET("v2/place?token=${Application.TOKEN}&lang=zh_CN")
    fun searchPlace(@Query("query") query: String) : retrofit2.Call<PlaceResponse>
}