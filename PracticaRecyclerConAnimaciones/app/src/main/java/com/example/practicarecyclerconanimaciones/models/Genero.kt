package com.example.practicarecyclerconanimaciones.models

import java.io.Serializable

class Genero(
    var id: Int,
    var nombre: String,
    var imagenUrl: String
) : Serializable {
    override fun toString(): String {
        return nombre
    }
}