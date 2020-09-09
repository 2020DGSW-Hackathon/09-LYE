package com.project.hackathon.data

data class Meal(
    //급식 정보를 담아주는 변수
    val meals: ArrayList<String?>
)

data class Schedule(
    //학사일정 정보를 담아주는 변수
    val schedules : List<DetailSchedule>
)

data class DetailSchedule(
    //이름을 저장하는 변수
    val name: String,
    //날짜를 저장하는 변수
    val date: String
)