package com.example.practico4solucion.models

typealias Libros = ArrayList<Libro>

data class Libro (
    val id: Int,
    val nombre: String,
    val autor: String,
    val editorial: String,
    val imagen: String,
    val sinopsis: String,
    val isbn: String,
    val calificacion: Long,
    val generos: Generos
)


