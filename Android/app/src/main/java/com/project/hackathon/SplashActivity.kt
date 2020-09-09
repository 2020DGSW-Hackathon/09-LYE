package com.project.hackathon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.simplecode.simIntent

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //메인 액티비티로 넘어간다
        simIntent(MainActivity::class.java)
    }
}