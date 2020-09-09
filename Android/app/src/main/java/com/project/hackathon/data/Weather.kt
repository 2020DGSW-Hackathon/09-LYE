package com.project.hackathon.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TotalWeather(
    var main : Main? = null,
    @SerializedName("weather")
    //날씨정보를 담아주는 변수
    var weatherList : ArrayList<Weather>? = null

): Serializable

data class Weather(
    var description : String? = null,
    var icon : String? = null,
    var id : Int? = null,
    var main : String? = null
): Serializable

data class Main(
    var humidity : Int? = null,
    var pressure : Int? = null,
    //온도
    var temp : Float? = null,
    //최대 온도
    var temp_max : Float? = null,
    //최소 온도
    var temp_min : Float? = null
): Serializable