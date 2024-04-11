package com.example.practicarecyclerview.models

import java.io.Serializable

class Person(
    var name: String = "",
    var lastName: String = "",
    var age: Int = 0,
    var email: String = "",
    var phone: String = ""
) : Serializable {
    var id = 0
}