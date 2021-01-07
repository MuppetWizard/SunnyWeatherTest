package com.muppet.sunnyweathertest.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.muppet.sunnyweathertest.logic.Repository
import com.muppet.sunnyweathertest.logic.mode.Location

class WeatherVm : ViewModel() {
    private val locationLiveData = MutableLiveData<Location>()

    var locationLng = ""

    var locationLat = ""

    var placeName = ""

    val weatherLiveData = Transformations.switchMap(locationLiveData){
        Repository.refreshWeather(it.lng,it.lat,placeName)
    }

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }
}