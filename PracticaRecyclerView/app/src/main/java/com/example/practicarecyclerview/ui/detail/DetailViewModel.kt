package com.example.practicarecyclerview.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicarecyclerview.models.Person

class DetailViewModel : ViewModel() {
    private val _currentPerson: MutableLiveData<Person> by lazy {
        MutableLiveData<Person>(null)
    }
    val currentPerson: LiveData<Person> get() = _currentPerson

    fun setPerson(person: Person) {
        _currentPerson.value = person
    }


}