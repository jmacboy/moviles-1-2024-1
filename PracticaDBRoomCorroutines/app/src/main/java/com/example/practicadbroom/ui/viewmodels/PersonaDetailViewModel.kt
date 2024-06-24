package com.example.practicadbroom.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicadbroom.models.Persona
import com.example.practicadbroom.repositories.PersonaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonaDetailViewModel : ViewModel() {
    private val _persona: MutableLiveData<Persona> by lazy {
        MutableLiveData<Persona>(null)
    }
    val persona: LiveData<Persona?> get() = _persona
    fun loadPersona(context: Context, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val persona = PersonaRepository.getById(context, id)
            _persona.postValue(persona)
        }
    }

    fun savePersona(
        context: Context,
        id: Int,
        name: String,
        lastName: String,
        age: Int,
        city: String,
        birthDate: String
    ) {
        val persona = Persona(name, lastName, age, city, birthDate)
        viewModelScope.launch(Dispatchers.IO) {
            if (id == 0) {
                PersonaRepository.insertPersona(context, persona)
            } else {
                persona.id = id
                PersonaRepository.updatePersona(context, persona)
            }
        }
    }
}