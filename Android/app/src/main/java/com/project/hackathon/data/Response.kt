package com.project.hackathon.data

data class Response<T>(
    val status: Int,
    val message: String,
    val data: T
)