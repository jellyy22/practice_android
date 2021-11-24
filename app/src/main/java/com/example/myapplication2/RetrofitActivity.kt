package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_library)

        // http://mellowcode.org/json/students/
        val retrofit = Retrofit.Builder().baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(RetrofitService::class.java)

        // GET 요청
        service.getStudentsList().enqueue(object : Callback<ArrayList<PersonFromServer>> {

            override fun onResponse(
                call: Call<ArrayList<PersonFromServer>>,
                response: Response<ArrayList<PersonFromServer>>
            ) {
                if (response.isSuccessful) {
                    val personList = response.body()
                    Log.d("retrofitt", "res: " + personList?.get(0)?.age)

                    val code = response.code()
                    Log.d("retrofitt", "code: $code")

                    val error = response.errorBody()
                    val header = response.headers()
                    Log.d("retrofitt", "header: $header")
                }
            }

            override fun onFailure(call: Call<ArrayList<PersonFromServer>>, t: Throwable) {

                Log.d("retrofitt", "ERROR")
                call.isCanceled
                call.isExecuted
                call.cancel()
            }
        })

        // POST 요청(1)
//        val params = HashMap<String, Any>()
//        params["name"] = "김치시러"
//        params["age"] = 20
//        params["intro"] = "Do you know ???"
//        service.createStudent(params).enqueue(object : Callback<PersonFromServer> {
//            override fun onResponse(
//                call: Call<PersonFromServer>,
//                response: Response<PersonFromServer>
//            ) {
//                if(response.isSuccessful) {
//                    val person = response.body()
//                    Log.d("retrofitt", "name : "+ person?.name)
//                }
//            }
//
//            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {
//
//            }
//        })

        // POST 요청(2)
        val person = PersonFromServer(name = "타코야끼", age = 7, intro = "타코야끼 7개 3000원")
        service.createStudentEasy(person).enqueue(object : Callback<PersonFromServer> {
            override fun onResponse(
                call: Call<PersonFromServer>,
                response: Response<PersonFromServer>
            ) {
                if(response.isSuccessful) {
                    val person = response.body()
                    Log.d("retrofitt", "name : "+ person?.name)
                }
            }

            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {
            }
        })
    }
}