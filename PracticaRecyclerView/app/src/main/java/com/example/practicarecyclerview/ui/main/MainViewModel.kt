package com.example.practicarecyclerview.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicarecyclerview.models.Person

class MainViewModel : ViewModel() {
    private val _personList: MutableLiveData<ArrayList<Person>> by lazy {
        MutableLiveData<ArrayList<Person>>(null)
    }
    val personList: LiveData<ArrayList<Person>> get() = _personList

    fun loadPeople() {
        _personList.value = arrayListOf(
            Person(
                "Juan",
                "Perez",
                12,
                "juan@test.com",
                "123456"
            ).apply { id = 1 },
            Person(
                "Maria",
                "Lopez",
                22,
                "maria@test.com",
                "1234567"
            ).apply { id = 2 },
            Person(
                "Pedro",
                "Gomez",
                32,
                "pedro@test.com",
                "12345678"
            ).apply { id = 3 }
        )
    }

    fun deletePerson(person: Person) {
        val list = _personList.value
        list?.remove(person)
        _personList.value = list
    }

    fun updatePerson(person: Person) {
        val list = _personList.value
        val index = list?.indexOfFirst { it.id == person.id }
        if (index != null) {
            list?.set(index, person)
        }
        _personList.value = list
    }

    fun insertPerson(person: Person) {
        val list = _personList.value
        person.id = list?.size?.plus(1) ?: 0
        list?.add(person)
        _personList.value = list
    }
}