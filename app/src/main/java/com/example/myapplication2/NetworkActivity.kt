package com.example.myapplication2

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_network.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.contracts.contract

class NetworkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        NetworkTask(recycler_person, LayoutInflater.from(this@NetworkActivity)).execute()
    }
}

class NetworkTask(val recyclerView: RecyclerView, val inflater: LayoutInflater) : AsyncTask<Any?, Any?, Array<PersonFromServer>>() {

    override fun onPostExecute(result: Array<PersonFromServer>?) {
        val adapter = PersonAdapter(result!!, inflater)
        recyclerView.adapter = adapter

        super.onPostExecute(result)
    }

    override fun doInBackground(vararg params: Any?): Array<PersonFromServer> {
        // 메인에서 돌릴 수 없다.
        val urlString: String = "http://mellowcode.org/json/students/"
        val url: URL = URL(urlString)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type", "application/json")

        var buffer = ""
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(InputStreamReader(connection.inputStream, "UTF-8"))
            buffer = reader.readLine()
        }

        val data = Gson().fromJson(buffer, Array<PersonFromServer>::class.java)
        return data
    }
}

class PersonAdapter(val personList: Array<PersonFromServer>, val inflater: LayoutInflater) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val age: TextView
        val intro: TextView

        init {
            name = itemView.findViewById(R.id.person_name)
            age = itemView.findViewById(R.id.person_age)
            intro = itemView.findViewById(R.id.person_ment)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.person_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = personList[position].name ?: ""
        holder.age.text = personList[position].age.toString()
        holder.intro.text = personList[position].intro ?: ""
    }

    override fun getItemCount(): Int {
        return personList.size
    }
}
