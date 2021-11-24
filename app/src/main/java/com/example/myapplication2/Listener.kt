package com.example.myapplication2

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView


class Listener : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listener)

        val textView: TextView = findViewById(R.id.hello)
        val imageView: ImageView = findViewById(R.id.image)

        val click = View.OnClickListener {
            textView.text = "안녕하세요~~"
            imageView.setImageResource(R.drawable.park)
        }

        textView.setOnClickListener(click)

    }
}