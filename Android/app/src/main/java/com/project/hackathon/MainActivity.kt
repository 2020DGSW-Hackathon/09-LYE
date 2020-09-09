package com.project.hackathon

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.hackathon.adapter.RcViewAdapter
import com.project.hackathon.data.*
import com.project.hackathon.network.MealClient
import com.project.hackathon.network.MealService
import com.project.hackathon.network.WeatherClient
import com.project.hackathon.network.WeatherService
import com.project.hackathon.room.DataBase
import com.project.simplecode.simIntentNoFinish
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val SCHOOL_ID = "7240393"
    val OFFICE_CODE = "D10"

    val LAT: Double = 35.663053
    val LON: Double = 128.413726
    val APP_ID: String = "09c8dfc52b7541d33c528d09a55e2c18"
    val UNITS: String = "metric"

    private var checkList = ArrayList<Check>()
    private val rcViewAdapter = RcViewAdapter()
    var checkDB: DataBase? = null

    lateinit var mealAPI: MealService
    lateinit var weatherAPI: WeatherService
    lateinit var mealRetrofit: Retrofit
    lateinit var weatherRetrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkDB = DataBase.getInstance(this)

        rcViewAdapter.setList(checkList)
        checkList.clear()
        checkList.addAll(checkDB?.dao()?.getAll()!!)
        rcViewAdapter.notifyDataSetChanged()
        main_rcView.layoutManager = LinearLayoutManager(this)
        main_rcView.adapter = rcViewAdapter

        weatherRetrofit = WeatherClient.getInstance()
        mealRetrofit = MealClient.getInstance()

        getMeal()
        getSchedule()

        getWeather()

        main_addItem.setOnClickListener {
            simIntentNoFinish(AddActivity::class.java)
        }
    }

    //날씨 정보를 받아오는 함수
    private fun getWeather() {
        weatherAPI = weatherRetrofit.create(WeatherService::class.java)
        weatherAPI.getWeather(LAT, LON, APP_ID, UNITS)
            .enqueue(object : Callback<TotalWeather> {
                //서버 통신 성공
                override fun onResponse(
                    call: Call<TotalWeather>,
                    response: retrofit2.Response<TotalWeather>
                ) {
                    var icon = response.body()?.weatherList?.get(0)?.icon

                    setWeatherImgComment(icon)

                    main_temp.text = String.format("%.1f", response.body()?.main?.temp) + "°C"
                }
                //서버 통신 성공
                override fun onFailure(call: Call<TotalWeather>, t: Throwable) {

                }
            })
    }
    //받아온 날씨 정보에 따라 표시되는 이미지 변경
    fun setWeatherImgComment(icon : String?){
        if (icon == "01d") {
            main_weather_img.setImageResource(R.drawable.sun)
            main_weather_comment.text = "아잇 눈부셔"
        } else if (icon == "01n") {
            main_weather_img.setImageResource(R.drawable.night)
            main_weather_comment.text = "소화도 할 겸 산책 어떤가요?"
        } else if (icon == "02d") {
            main_weather_img.setImageResource(R.drawable.sun_cloud)
            main_weather_comment.text = "오늘 야자는 강당이다"
        } else if (icon == "02n") {
            main_weather_img.setImageResource(R.drawable.night_cloud)
            main_weather_comment.text = "창문 열고 자면 추울 것 같아요"
        } else if (icon == "03d" || icon == "03n" || icon == "04d" || icon == "04n") {
            main_weather_img.setImageResource(R.drawable.cloud)
            main_weather_comment.text = "어두컴컴하니깐 텐션 떨어져요"
        } else if (icon == "09d" || icon == "09n" || icon == "10n" || icon == "10d") {
            main_weather_img.setImageResource(R.drawable.rain)
            main_weather_comment.text = "실내점호! 실내점호! 실내점호!"
        } else if (icon == "11d" || icon == "11n") {
            main_weather_img.setImageResource(R.drawable.thunder)
            main_weather_comment.text = "좀 더 격렬하게 실내점호! 실내점호!"
        } else if (icon == "13d" || icon == "13n") {
            main_weather_img.setImageResource(R.drawable.snow)
            main_weather_comment.text = "눈이 와요! 우와아"
        } else if (icon == "50d" || icon == "50n") {
            main_weather_img.setImageResource(R.drawable.mist)
            main_weather_comment.text = "조금 습하네요"
        }
    }

    //급식 정보를 받아오는 코드
    private fun getMeal() {

        mealAPI = mealRetrofit.create(MealService::class.java)
        mealAPI.getMeal(SCHOOL_ID, OFFICE_CODE, today())
            .enqueue(object : Callback<Response<Meal>> {
                //서버 통신 성공
                override fun onResponse(
                    call: Call<Response<Meal>>,
                    response: retrofit2.Response<Response<Meal>>
                ) {

                    var breakfast = response.body()?.data?.meals?.get(0)
                    var lunch = response.body()?.data?.meals?.get(1)
                    var dinner = response.body()?.data?.meals?.get(2)
                    //조건에 따라 급식을 보여주거나 안보여줌
                    if (breakfast != null) {
                        breakfast_menu.text = Html.fromHtml(breakfast.replace("[0-9]".toRegex(),"").replace(".",""))
                    }
                    else{
                        breakfast_menu.text = "오늘의 아침은 없습니다."
                    }
                    if (lunch != null) {
                        lunch_menu.text = Html.fromHtml(lunch.replace("[0-9]".toRegex(),"").replace(".",""))
                    }
                    else{
                        lunch_menu.text = "오늘의 점심은 없습니다."
                    }
                    if (dinner != null) {
                        dinner_menu.text = Html.fromHtml(dinner.replace("[0-9]".toRegex(),"").replace(".",""))
                    }else{
                        dinner_menu.text = "오늘의 저녁은 없습니다."
                    }
                }
                //서버 통신 실패
                override fun onFailure(call: Call<Response<Meal>>, t: Throwable) {

                }
            })
    }
    //학사일정 정보를 받아오는 함수
    private fun getSchedule() {
        mealAPI = mealRetrofit.create(MealService::class.java)
        mealAPI.getSchedule(SCHOOL_ID, OFFICE_CODE, today())
            .enqueue(object : Callback<Response<Schedule>> {
                //서버 통신 성공
                override fun onResponse(
                    call: Call<Response<Schedule>>,
                    response: retrofit2.Response<Response<Schedule>>
                ) {

                    if (response.body()?.status == null) {
                        main_schedule.text = "오늘은 학사일정이 없습니다."
                    } else {
                        main_schedule.text = response.body()?.data?.schedules?.get(0)?.name
                    }

                }
                //서버 통신 실패
                override fun onFailure(call: Call<Response<Schedule>>, t: Throwable) {

                }
            })
    }

    private fun today(): String {
        var now: Long = System.currentTimeMillis()
        var date: Date = Date(now)
        var dateFormat: SimpleDateFormat = SimpleDateFormat("yyyyMMdd")
        var getTime: String = dateFormat.format(date)

        return getTime
    }

}