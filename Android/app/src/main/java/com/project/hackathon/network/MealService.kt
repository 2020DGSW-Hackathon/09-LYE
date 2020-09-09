package com.project.hackathon.network

import com.project.hackathon.data.Meal
import com.project.hackathon.data.Response
import com.project.hackathon.data.Schedule
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
//급식 정보와 학사일정을 받아오는 인터페이스
interface MealService {
    //급식 정보 받아오기
    @GET("meals")
    fun getMeal(
        @Query("school_id") id : String,
        @Query("office_code") code : String,
        @Query("date") date: String
    ): Call<Response<Meal>>
    //학사일정 정보 받아오기
    @GET("schedule")
    fun getSchedule(
        @Query("school_id") id : String,
        @Query("office_code") code : String,
        @Query("date") date : String
    ): Call<Response<Schedule>>

}