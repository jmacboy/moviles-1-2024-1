package com.example.practicanavegacion.models

class User (
    var id: Int,
    var name: String,
    var email: String,
    var password: String,
    var isAdmin: Boolean = false
) {
    override fun toString(): String {
        return "User(id=$id, name='$name', email='$email', password='$password')"
    }
}