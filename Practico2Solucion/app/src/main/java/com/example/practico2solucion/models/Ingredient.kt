package com.example.practico2solucion.models

import java.io.Serializable

class Ingredient(
    var name: String,
    var id: Int,
    var selected: Boolean,
    var imagenUrl: String
): Serializable