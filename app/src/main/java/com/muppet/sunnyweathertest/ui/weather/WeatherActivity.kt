package com.muppet.sunnyweathertest.ui.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.muppet.sunnyweathertest.R

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
    }
}