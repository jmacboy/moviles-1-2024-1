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
    var profilePicture = "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50?s=200"
}