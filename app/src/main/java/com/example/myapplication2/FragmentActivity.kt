package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        Log.d("life_cycle", "onCreate")

        val click: Button = findViewById(R.id.button)
        val click2: Button = findViewById(R.id.button2)

        val fragmentOne = FragmentOne()
        // 프래그먼트에 data를 넣어주는 방법
        val bundle : Bundle = Bundle()
        bundle.putString("hello", "hello")
        fragmentOne.arguments = bundle

        click.setOnClickListener {
            // 프래그먼트를 동적으로 작동하는 방법
            val fragmentManager = supportFragmentManager

            // Transaction
            // 작업의 단위 -> 시작과 끝이 있다.
            val fragmentTransaction = fragmentManager.beginTransaction()
            // replace/add
            fragmentTransaction.replace(R.id.container, fragmentOne)
            fragmentTransaction.commit()
            // 끝을 내는 방법
            // commit()     -> 시간 날 때 (좀 더 안정적)
            // commitNow()  -> 지금당장
        }

        click2.setOnClickListener {
            // 프래그먼트 remove/detach 하는 방법
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.detach(fragmentOne)
            fragmentTransaction.commit()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("life_cycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life_cycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("life_cycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life_cycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life_cycle", "onDestroy")
    }

}