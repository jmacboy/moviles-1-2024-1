package com.example.practicamvvmform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicamvvmform.models.FakeDB
import com.example.practicamvvmform.models.User

class RegisterViewModel: ViewModel() {
    private val _goToLoginScreen: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val goToLoginScreen: LiveData<Boolean> get() = _goToLoginScreen

    fun registerUser(name: String, lastName: String, username: String, password: String) {
        val user = User()
        user.name = name
        user.lastName = lastName
        user.username = username
        user.password = password

        FakeDB.users.add(user)
        _goToLoginScreen.value = true
    }
}