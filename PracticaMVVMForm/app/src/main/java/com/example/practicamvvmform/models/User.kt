package com.example.practicamvvmform.models

class User {
    var name: String = ""
    var lastName: String = ""
    var username: String = ""
    var password: String = ""
    override fun toString(): String {
        return "$name $lastName"
    }
}