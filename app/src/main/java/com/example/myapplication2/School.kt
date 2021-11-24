package com.example.myapplication2

import io.realm.RealmObject

open class School : RealmObject() {
    var name: String? = null
    var location: String? = null

}