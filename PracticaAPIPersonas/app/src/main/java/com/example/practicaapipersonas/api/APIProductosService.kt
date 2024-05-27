package com.example.practicaapipersonas.api

import com.example.practicaapipersonas.models.Categoria
import com.example.practicaapipersonas.models.Categorias
import com.example.practicaapipersonas.models.Producto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIProductosService {
    @GET("productos")
    fun getProductos(): Call<Producto>

    @GET("categorias")
    fun getCategorias(): Call<Categorias>

    @POST("categorias")
    fun insertCategoria(
        @Body categoria: Categoria
    ): Call<Categoria>

}