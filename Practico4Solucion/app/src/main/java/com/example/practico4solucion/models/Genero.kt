package com.example.practico4solucion.models

typealias Generos = ArrayList<Genero>


data class Genero(
    val id: Int,
    val nombre: String,
    val libros: Libros
)

