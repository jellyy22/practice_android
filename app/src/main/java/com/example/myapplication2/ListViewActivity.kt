package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val carList = ArrayList<CarForList>()
        for (i in 0 until 10) {
            carList.add(CarForList("$i 번째 자동차", "$i 순위 엔진"))
        }

//        val adapter = ListViewAdapter(carList, this@ListViewActivity) ----
        val adapter = ListViewAdapter(carList, LayoutInflater.from(this@ListViewActivity))
        val listView = findViewById<ListView>(R.id.listView)

        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val carName = (adapter.getItem(position) as CarForList).name
            val carEngine = (adapter.getItem(position) as CarForList).engine

            Toast.makeText(this@ListViewActivity, "$carName $carEngine", Toast.LENGTH_SHORT).show()

        }
    }
}

//class ListViewAdapter(val carForList: ArrayList<CarForList>, val context: LayoutInflater) :
//    BaseAdapter() {

class ListViewAdapter(val carForList: ArrayList<CarForList>, val layoutInflater: LayoutInflater) :
    BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.item_view, null)
            holder = ViewHolder()

            holder.carName = view.findViewById(R.id.car_name)
//            holder.carEngine = view.findViewById(R.id.car_engine)

            view.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            view = convertView
        }

        holder.carName?.text = carForList[position].name
        holder.carEngine?.text = carForList[position].engine
        return view

//        val layoutInflater = LayoutInflater.from(context)


//        val view = layoutInflater.inflate(R.layout.item_view, null)
//
//        val carNameTextView = view.findViewById<TextView>(R.id.car_name)
//        val carEngineTextView = view.findViewById<TextView>(R.id.car_engine)
//
//        carNameTextView.text = carForList[position].name
//        carEngineTextView.text = carForList[position].engine
//
//        return view

    }

    override fun getCount(): Int {
        // 그리고자 하는 아이템 리스트의 전체 개수
        return carForList.size
    }

    override fun getItem(position: Int): Any {
        // 그리고자 하는 아이템 리스트의 하나(포지션에 해당하는)
        return carForList[position]
    }

    override fun getItemId(position: Int): Long {
        // 해당 포지션에 위치해 있는 아이템뷰의 아이디 설정
        return position.toLong()
    }
}

class ViewHolder() {
    var carName: TextView? = null
    var carEngine: TextView? = null

}