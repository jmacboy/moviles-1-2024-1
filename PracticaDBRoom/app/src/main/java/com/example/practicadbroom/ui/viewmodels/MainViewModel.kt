package com.example.practicadbroom.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicadbroom.models.Persona
import com.example.practicadbroom.repositories.PersonaRepository

class MainViewModel : ViewModel() {
    private val _personas: MutableLiveData<List<Persona>> by lazy {
        MutableLiveData<List<Persona>>(null)
    }
    val personas: LiveData<List<Persona>> get() = _personas


    fun loadPersonas(context: Context) {
        val listaPersonas = PersonaRepository.getAll(context)
        _personas.value = listaPersonas
    }

    fun deletePersona(context: Context, persona: Persona) {
        PersonaRepository.deletePersona(context, persona)
        loadPersonas(context)
    }

}