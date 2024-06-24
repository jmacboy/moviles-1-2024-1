package com.example.practicadbroom.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practicadbroom.dao.PersonaDao
import com.example.practicadbroom.models.Persona

@Database(entities = [Persona::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personaDao(): PersonaDao
}
