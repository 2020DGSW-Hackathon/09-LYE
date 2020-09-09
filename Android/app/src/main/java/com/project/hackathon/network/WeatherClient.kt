package com.project.hackathon.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//날씨 정보를 받아온다
object WeatherClient {

    private var instance : Retrofit? = null

    fun getInstance() : Retrofit {
        if(instance == null){
            instance = Retrofit.Builder()
                //날씨 base url
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }


}