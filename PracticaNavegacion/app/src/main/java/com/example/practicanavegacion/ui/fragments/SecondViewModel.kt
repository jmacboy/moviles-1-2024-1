package com.example.practicanavegacion.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicanavegacion.models.User
import com.example.practicanavegacion.repositories.FakeUsersRepository

class SecondViewModel : ViewModel() {
    private val _errorMsg: MutableLiveData<String> by lazy {
        MutableLiveData<String>(null)
    }
    val errorMsg: LiveData<String> get() = _errorMsg
    private val _user: MutableLiveData<User> by lazy {
        MutableLiveData<User>(null)
    }
    val user: LiveData<User> get() = _user
    fun login(email: String, password: String) {
        val theUser = FakeUsersRepository.loginClient(email, password)
        if (theUser == null) {
            _errorMsg.postValue("Usuario o contraseña incorrectos")
            return
        }
        _user.postValue(theUser)
    }
}