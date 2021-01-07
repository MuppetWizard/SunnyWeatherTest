package com.muppet.sunnyweathertest.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.muppet.sunnyweathertest.logic.Repository
import com.muppet.sunnyweathertest.logic.mode.Place

class PlaceVm : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData) {query ->
        Repository.searchPlaces(query)
    }

    fun searchPlace(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = Repository.savePlace(place)

    fun getSavedPlace() = Repository.getSavedPlace()

    fun isPlaceSaved() = Repository.isPlaceSaved()

}