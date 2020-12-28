package com.muppet.sunnyweathertest

import android.app.Application
import android.content.Context

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object{
        lateinit var context : Context

        const val TOKEN = "kkHFddvog9q6AQvi" //彩云天气令牌
    }
}