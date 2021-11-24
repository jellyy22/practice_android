package com.example.myapplication2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_instagram_user_info.*

class InstagramUserInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instagram_user_info)

        all_list.setOnClickListener {
            startActivity(Intent(this, InstagramPostListActivity::class.java))
        }

        my_list.setOnClickListener {
            startActivity(Intent(this, InstagramMyPostListActivity::class.java))
        }

        upload.setOnClickListener {
            startActivity(Intent(this, InstagramUploadActivity::class.java))
        }

        logout.setOnClickListener {
//            val token = sp.getString("login_sp", "null")
            val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("login_sp", "null")
            editor.commit()
            (application as MasterApplication).createRetrofit()
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}