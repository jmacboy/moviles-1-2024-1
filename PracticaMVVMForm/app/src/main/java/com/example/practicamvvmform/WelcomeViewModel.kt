package com.example.practicamvvmform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicamvvmform.models.FakeDB
import com.example.practicamvvmform.models.User

class WelcomeViewModel: ViewModel() {
    private val _personList: MutableLiveData<ArrayList<User>> by lazy {
        MutableLiveData<ArrayList<User>>(null)
    }
    val personList: LiveData<ArrayList<User>> get() = _personList


    fun loadPersonList(){
        _personList.value = FakeDB.users as ArrayList<User>
    }
}