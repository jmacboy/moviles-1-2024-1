package com.example.practicaapipersonas.models

typealias Productos = ArrayList<Producto>

data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precioActual: String,
    val createdAt: String,
    val updatedAt: String
)
