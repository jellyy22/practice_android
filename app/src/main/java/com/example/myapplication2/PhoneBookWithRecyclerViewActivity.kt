package com.example.myapplication2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PhoneBookWithRecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_book_with_recycler_view_acivity)

        val phoneBook = createFakePhoneBook(30)
        val phoneBookRecyclerAdapter = PhoneBookRecyclerAdapter(
            phoneBook,
            LayoutInflater.from(this@PhoneBookWithRecyclerViewActivity),
            this@PhoneBookWithRecyclerViewActivity
        )

        val recyclerView = findViewById<RecyclerView>(R.id.phonebook_recycler_view)
        with(recyclerView) {
            this.adapter = phoneBookRecyclerAdapter
            this.layoutManager = LinearLayoutManager(this@PhoneBookWithRecyclerViewActivity)
        }
    }

    fun createFakePhoneBook(fakeNum: Int = 10, phoneBook: PhoneBook = PhoneBook()): PhoneBook {
        for (i in 0 until fakeNum) {
            phoneBook.addPerson(Person("$i 번째 사람", "$i 번째 전화번호"))
        }
        return phoneBook
    }
}

class PhoneBookRecyclerAdapter(
    val phoneBookList: PhoneBook,
    val inflater: LayoutInflater,
    val activity: Activity
) :
    RecyclerView.Adapter<PhoneBookRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personName: TextView

        init {
            personName = itemView.findViewById(R.id.person_name)
            itemView.setOnClickListener {

                val intent = Intent(activity, PhoneBookDetailActivity::class.java)
                intent.putExtra("name", phoneBookList.personList[adapterPosition].name)
                intent.putExtra("number", phoneBookList.personList[adapterPosition].number)
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.phonebook_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.personName.text = phoneBookList.personList[position].name
    }

    override fun getItemCount(): Int {
        return phoneBookList.personList.size
    }
}