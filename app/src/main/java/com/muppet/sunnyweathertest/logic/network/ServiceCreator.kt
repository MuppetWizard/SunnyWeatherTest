package com.muppet.sunnyweathertest.logic.network

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.muppet.sunnyweathertest.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceCreator {
    private const val BASE_URL = "https://api.caiyunapp.com/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getClient().build())
        .build()

    private fun getClient() : OkHttpClient.Builder{
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(15,TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = LoggingInterceptor.Builder()
                .setLevel(Level.BASIC)
                .request("请求")
                .response("响应")
                .build()
            httpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return httpClientBuilder
    }

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}