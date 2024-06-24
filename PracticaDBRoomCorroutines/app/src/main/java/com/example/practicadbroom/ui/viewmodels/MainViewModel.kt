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

class MainViewModel : ViewModel() {
    private val _personas: MutableLiveData<List<Persona>> by lazy {
        MutableLiveData<List<Persona>>(null)
    }
    val personas: LiveData<List<Persona>> get() = _personas


    fun loadPersonas(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val listaPersonas = PersonaRepository.getAll(context)
            _personas.postValue(listaPersonas)
        }
    }

    fun deletePersona(context: Context, persona: Persona) {
        viewModelScope.launch(Dispatchers.IO) {
            PersonaRepository.deletePersona(context, persona)
        }
        loadPersonas(context)
    }

}