package com.example.practicamvvmform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicamvvmform.models.FakeDB

class MainViewModel : ViewModel() {
    private val _errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _goToWelcomeScreen: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val goToWelcomeScreen: LiveData<Boolean> get() = _goToWelcomeScreen

    fun signIn(user: String, password: String) {
        val filteredUser = FakeDB.users.filter { it.username == user && it.password == password }
        if (filteredUser.isNotEmpty()) {
            _goToWelcomeScreen.value = true
        } else {
            _errorMessage.value = "Usuario o contraseña incorrectos"
        }
    }
}