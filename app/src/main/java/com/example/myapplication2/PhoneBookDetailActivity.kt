package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class PhoneBookDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_book_detail)

        getPersonInfoAndDraw()

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            // 뒤로가기와 동일한 동작
            onBackPressed()
        }
    }

    fun getPersonInfoAndDraw() {
        val name = intent.getStringExtra("name")
        val number = intent.getStringExtra("number")

        val p_name = findViewById<TextView>(R.id.person_detail_name)
        val p_num = findViewById<TextView>(R.id.person_detail_number)

        p_name.text = name
        p_num.text = number

    }
}