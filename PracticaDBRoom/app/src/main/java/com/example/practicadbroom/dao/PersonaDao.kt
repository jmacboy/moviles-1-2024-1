package com.example.practicadbroom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.practicadbroom.models.Persona

@Dao
interface PersonaDao {
    @Query("SELECT * FROM Persona")
    fun getAll(): List<Persona>

    @Query("SELECT * FROM Persona WHERE id = :id")
    fun getById(id: Int): Persona

    @Insert
    fun insert(persona: Persona)
}