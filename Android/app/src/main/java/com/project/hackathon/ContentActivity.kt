package com.project.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.project.hackathon.room.DataBase
import com.project.simplecode.simIntent
import com.project.simplecode.simToastShort
import kotlinx.android.synthetic.main.activity_content.*

class ContentActivity : AppCompatActivity() {

    var checkDB: DataBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        checkDB = DataBase.getInstance(this)

        var des = intent.getStringExtra("des")
        var id = intent.getLongExtra("id", 0)

        content_editText.setText(des)
        //content_edit_button 클릭시 작성한 메모 수정
        content_edit_button.setOnClickListener {
            checkDB?.dao()?.update(content_editText.text.toString(), id)
            simToastShort("수정되었습니다.")
            simIntent(MainActivity::class.java)
        }
        //content_del_button 클릭시 작성한 메모 삭제
        content_del_button.setOnClickListener {
            checkDB?.dao()?.delete(id)
            simToastShort("삭제되었습니다.")
            simIntent(MainActivity::class.java)
        }

    }


}