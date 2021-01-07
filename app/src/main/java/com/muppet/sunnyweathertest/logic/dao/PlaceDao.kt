package com.muppet.sunnyweathertest.logic.dao


import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.muppet.sunnyweathertest.MyApplication
import com.muppet.sunnyweathertest.logic.mode.Place

object PlaceDao {

    fun savePlace(place: Place) {
        sharePreference().edit {
            putString("place",Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place{
        val placeJson = sharePreference().getString("place","")
        return Gson().fromJson(placeJson,Place::class.java)
    }

    fun isPlaceSaved() = sharePreference().contains("place")

    private fun sharePreference() =
        MyApplication.context.getSharedPreferences("sunny_weather",Context.MODE_PRIVATE)

}