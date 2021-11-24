package com.example.myapplication2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ContextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_context)

        val context: Context = this
//        val applicationContext : Context = getApplicationContext()



    }
}