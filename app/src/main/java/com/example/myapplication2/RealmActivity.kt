package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm)

        Realm.init(this@RealmActivity)
        val config: RealmConfiguration =
            RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()

        Realm.setDefaultConfiguration(config)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
//        realm.commitTransaction()


        val button_save = findViewById<Button>(R.id.button_save)
        button_save.setOnClickListener {
            realm.executeTransaction {
                with(it.createObject(School::class.java)) {
                    this.name = "어떤 대학교"
                    this.location = "서울"
                }
            }
        }

        val button_load = findViewById<Button>(R.id.button_load)
        button_load.setOnClickListener {
            realm.executeTransaction {
                val data = it.where(School::class.java).findFirst()
                Log.d("dataa", "data : $data")

            }
        }

        val button_delete = findViewById<Button>(R.id.button_delete)
        button_delete.setOnClickListener {
            realm.executeTransaction {
                it.where(School::class.java).findAll().deleteAllFromRealm()
//                it.where(School::class.java).findFirst()?.deleteFromRealm()
            }
        }
    }
}