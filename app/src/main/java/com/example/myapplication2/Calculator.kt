package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Switch
import android.widget.TextView

class Calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        var new = ""
        var old = 0

        val textView1: TextView = findViewById(R.id.num1)
        val textView2: TextView = findViewById(R.id.num2)
        val textView3: TextView = findViewById(R.id.num3)
        val textView4: TextView = findViewById(R.id.num4)
        val textView5: TextView = findViewById(R.id.num5)
        val textView6: TextView = findViewById(R.id.num6)
        val textView7: TextView = findViewById(R.id.num7)
        val textView8: TextView = findViewById(R.id.num8)
        val textView9: TextView = findViewById(R.id.num9)
        val textView0: TextView = findViewById(R.id.num0)
        val textViewCA: TextView = findViewById(R.id.numCA)
        val textViewPLUS: TextView = findViewById(R.id.numPlus)
        val textViewResult: TextView = findViewById(R.id.result)

        textView1.setOnClickListener {
            new += "1"
            textViewResult.text = new
        }

        textView2.setOnClickListener {
            new += "2"
            textViewResult.text = new
        }

        textView3.setOnClickListener {
            new += "3"
            textViewResult.text = new
        }

        textView4.setOnClickListener {
            new += "4"
            textViewResult.text = new
        }

        textView5.setOnClickListener {
            new += "5"
            textViewResult.text = new
        }

        textView6.setOnClickListener {
            new += "6"
            textViewResult.text = new
        }

        textView7.setOnClickListener {
            new += "7"
            textViewResult.text = new
        }

        textView8.setOnClickListener {
            new += "8"
            textViewResult.text = new
        }

        textView9.setOnClickListener {
            new += "9"
            textViewResult.text = new
        }

        textView0.setOnClickListener {
            new += "0"
            textViewResult.text = new
        }

        textViewPLUS.setOnClickListener {

            if(new == ""){
                new = "0"
            }
            old += new.toInt()

            textViewResult.text = (old).toString()

            new = ""

        }

        textViewCA.setOnClickListener {
            new = "0"
            old = 0
            textViewResult.text = "0"
        }
    }
}
