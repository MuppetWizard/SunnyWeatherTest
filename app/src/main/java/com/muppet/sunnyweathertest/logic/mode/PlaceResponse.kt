package com.muppet.sunnyweathertest.logic.mode

import com.google.gson.annotations.SerializedName

data class PlaceResponse(val status: String,val places:List<Place>)

//通过 @SerializedName 注释让kotlin字段与json字段建立映射关系
data class Place(val name : String, val location: Location, @SerializedName("formatted_address") val address: String)

data class Location(val lng: String, val lat: String)