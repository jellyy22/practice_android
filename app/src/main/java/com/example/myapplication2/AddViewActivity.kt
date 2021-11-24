package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import java.util.zip.Inflater

class AddViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_view)

        // 아이템 리스트 준비
        val carList = ArrayList<CarForList>()
        for (i in 0 until 10) {
            carList.add(CarForList("" + i + " 번째 자동차", "" + i + "순위 엔진"))
        }

        // 같은 방법
//        val container : LinearLayout = findViewById(R.id.addview_container)

        val container = findViewById<LinearLayout>(R.id.addview_container)

        val inflater = LayoutInflater.from(this@AddViewActivity)

        for (i in 0 until carList.size) {
            val itemView = inflater.inflate(R.layout.item_view, null)

            val carNameView = itemView.findViewById<TextView>(R.id.car_name)
            val carEngineView = itemView.findViewById<TextView>(R.id.car_engine)

            carNameView.text = carList[i].name
            carEngineView.text = carList[i].engine
            container.addView(itemView)
        }
    }
}

class CarForList(val name: String, val engine: String) {

}