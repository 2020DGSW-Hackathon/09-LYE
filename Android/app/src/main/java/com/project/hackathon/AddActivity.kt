package com.project.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.hackathon.data.Check
import com.project.hackathon.room.DataBase
import com.project.simplecode.simIntent
import kotlinx.android.synthetic.main.activity_add.*
//메모를 추가해주는 Activity
class AddActivity : AppCompatActivity() {

    private var CheckDB : DataBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        CheckDB = DataBase.getInstance(this)
        //add_button을 클릭시 db에 입력한 텍스트를 저장해준다
        add_button.setOnClickListener {
            CheckDB?.dao()?.insert(Check(0, add_edit.text.toString()))
            simIntent(MainActivity::class.java)
        }
    }
}