package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class LibraryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        val glide1 : ImageView = findViewById(R.id.glide1)
        Glide.with(this@LibraryActivity)
            .load("https://cdn.pixabay.com/photo/2021/08/03/11/48/canal-6519196_960_720.jpg")
            .centerCrop()
            .into(glide1)

        val glide2 : ImageView = findViewById(R.id.glide2)
        Glide.with(this@LibraryActivity)
            .load("https://cdn.pixabay.com/photo/2021/08/03/11/48/canal-6519196_960_720.jpg")
            .into(glide2)
    }
}