package com.example.myapplication2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class PhoneBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_book)

        val phoneBook = createFakePhoneBook(30)
        createPhonebookList(phoneBook)

    }

    fun createFakePhoneBook(fakeNum: Int = 10, phoneBook: PhoneBook = PhoneBook()): PhoneBook {
        for (i in 0 until fakeNum) {
            phoneBook.addPerson(Person("$i 번째 사람", "$i 번째 전화번호"))
        }
        return phoneBook
    }

    fun createPhonebookList(phoneBook: PhoneBook) {
        val layoutInflater = LayoutInflater.from(this@PhoneBookActivity)
        val container = findViewById<LinearLayout>(R.id.phonebook_list_container)

        for (i in 0 until phoneBook.personList.size) {
            val itemView = layoutInflater.inflate(R.layout.phonebook_item, null)
            val personNameView = itemView.findViewById<TextView>(R.id.person_name)
            personNameView.text = phoneBook.personList[i].name
            addSetOnClickListener(phoneBook.personList[i], itemView)
            container.addView(itemView)
        }
    }

    fun addSetOnClickListener(person: Person, view: View) {
        view.setOnClickListener {
            val intent = Intent(this@PhoneBookActivity, PhoneBookDetailActivity::class.java)
            intent.putExtra("name", person.name)
            intent.putExtra("number", person.number)
            startActivity(intent)
        }
    }
}

class PhoneBook() {
    val personList = ArrayList<Person>()

    fun addPerson(person: Person) {
        personList.add(person)
    }
}

class Person(val name: String, val number: String) {

}