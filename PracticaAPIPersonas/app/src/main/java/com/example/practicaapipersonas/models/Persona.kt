package com.example.practicaapipersonas.models

typealias Personas = ArrayList<Persona>

data class Persona(
    var id: Int = 0,
    var nombres: String,
    var apellidos: String,
    var edad: Int,
    var ciudad: String,
    var fechaNacimiento: String
) {
    var createdAt: String? = null
    var updatedAt: String? = null
}
