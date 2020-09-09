package com.project.hackathon.network


import com.project.hackathon.data.TotalWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    //날씨 정보 받아오기
    @GET("data/2.5/weather/")
    fun getWeather(
        @Query("lat") lat : Double,
        @Query("lon") lon : Double,
        @Query("APPID") appID : String,
        @Query("units") units : String
    ): Call<TotalWeather>

}