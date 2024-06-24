package com.example.practicadbroom.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.practicadbroom.models.Persona

@Dao
interface PersonaDao {
    @Query("SELECT * FROM Persona")
    fun getAll(): List<Persona>

    @Query("SELECT * FROM Persona WHERE id = :id")
    fun getById(id: Int): Persona

    @Insert
    fun insert(persona: Persona)

    @Update
    fun update(persona: Persona)

    @Delete
    fun delete(persona: Persona)
}