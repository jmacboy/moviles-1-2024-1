package com.example.practicadbroom.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Persona(
    var nombre: String,
    var apellido: String,
    var edad: Int,
    var ciudad: String,
    var fechaNacimiento: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    override fun toString(): String {
        return "$nombre $apellido"
    }

}