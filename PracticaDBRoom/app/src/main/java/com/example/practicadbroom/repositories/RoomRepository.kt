package com.example.practicadbroom.repositories

import android.content.Context
import androidx.room.Room
import com.example.practicadbroom.db.AppDatabase

object RoomRepository {
    fun getDbInstance(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
    }
}