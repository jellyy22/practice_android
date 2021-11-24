package com.example.myapplication2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import java.util.*

class OpenInternet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_internet)

        val button : Button = findViewById(R.id.open)
        val addresses : EditText = findViewById(R.id.address_internet)

        button.setOnClickListener{
            val address = addresses.text.toString()
            Log.d("click", address)

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(address))
            startActivity(intent)
        }

        //text 감지
        addresses.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("edit", "beforeTextChanged : $s")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("edit", "onTextChanged : $s")
            }

            override fun afterTextChanged(s: Editable?) {
                if(s.toString() == "abc"){
                    Log.d("edit", "text is abc")
                }

                Log.d("edit", "afterTextChanged : $s")
            }
        })

    }
}