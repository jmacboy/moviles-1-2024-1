package com.example.practicaapipersonas.models

typealias Categorias = ArrayList<Categoria>

data class Categoria(
    var nombre: String
) {
    var id: Int? = null
    var createdAt: String? = null
    var updatedAt: String? = null
    var productos: List<Producto> = emptyList()

}