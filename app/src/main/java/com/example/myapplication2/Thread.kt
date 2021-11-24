package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import java.lang.Thread

class Thread : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        val button: Button = findViewById(R.id.button)

        val runnable: Runnable = object : Runnable {
            override fun run(){
                Log.d("thread-1", "Thread1 is made")
            }
        }

        val thread: Thread = Thread(runnable)
        button.setOnClickListener {
            thread.start()
        }

        // 람다를 사용하지 않는 방식
        Thread(object : Runnable {
            override fun run() {
                Log.d("thread-1", "Thread2 is made")
            }
        }).start()

        // 람다방식
        Thread(Runnable {
            Thread.sleep(2000)
            Log.d("thread-1", "Thread3 is made")
            runOnUiThread {
                button.setBackgroundColor(getColor(R.color.textview_color))
            }
        }).start()


    }
}