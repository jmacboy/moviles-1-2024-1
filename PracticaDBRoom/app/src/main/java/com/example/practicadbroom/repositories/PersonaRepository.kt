package com.example.practicadbroom.repositories

import android.content.Context
import com.example.practicadbroom.models.Persona

object PersonaRepository {
    fun getAll(context: Context): List<Persona> {
        val db = RoomRepository.getDbInstance(context)
        return db.personaDao().getAll()
    }

    fun insertPersona(context: Context, persona: Persona) {
        val db = RoomRepository.getDbInstance(context)
        db.personaDao().insert(persona)
    }

    fun getById(context: Context, id: Int): Persona {
        val db = RoomRepository.getDbInstance(context)
        return db.personaDao().getById(id)
    }

    fun updatePersona(context: Context, persona: Persona) {
        val db = RoomRepository.getDbInstance(context)
        db.personaDao().update(persona)
    }

    fun deletePersona(context: Context, persona: Persona) {
        val db = RoomRepository.getDbInstance(context)
        db.personaDao().delete(persona)
    }
}