package com.example.practicarecyclerconanimaciones.models

class Cancion(
    var id: Int,
    var nombre: String,
    var artista: String,
    var genero: Genero
) {
    override fun toString(): String {
        return "$nombre - $artista"
    }
}