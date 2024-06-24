package com.example.practicaapipersonas.api

import com.example.practicaapipersonas.models.Categoria
import com.example.practicaapipersonas.models.Categorias
import com.example.practicaapipersonas.models.Producto
import com.example.practicaapipersonas.models.Productos
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface APIProductosService {
    @GET("productos")
    fun getProductos(): Call<Productos>

    @GET("productos/{id}")
    fun getProductoById(
        @Path("id") id: Int
    ): Call<Producto?>

    @POST("productos")
    fun insertProducto(
        @Body producto: Producto
    ): Call<Producto>

    @PUT("productos/{id}")
    fun updateProducto(
        @Body producto: Producto,
        @Path("id") id: Int
    ): Call<Producto>

    @DELETE("productos/{id}")
    fun deleteProducto(
        @Path("id") id: Int
    ): Call<Void>


    @GET("categorias")
    fun getCategorias(): Call<Categorias>

    @GET("categorias/{id}")
    fun getCategoriaById(
        @Path("id") id: Int
    ): Call<Categoria?>

    @POST("categorias")
    fun insertCategoria(
        @Body categoria: Categoria
    ): Call<Categoria>

    @PUT("categorias/{id}")
    fun updateCategoria(
        @Body categoria: Categoria,
        @Path("id") id: Int
    ): Call<Categoria>

    @DELETE("categorias/{id}")
    fun deleteCategoria(
        @Path("id") id: Int
    ): Call<Void>

}