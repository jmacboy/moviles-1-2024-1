package com.example.practico4solucion.api

import com.example.practico4solucion.models.Genero
import com.example.practico4solucion.models.Generos
import com.example.practico4solucion.models.Libro
import com.example.practico4solucion.models.Libros
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface APILibrosService {
    @GET("libros")
    fun getLibros(): Call<Libros>

    @GET("libros/{id}")
    fun getLibroById(
        @Path("id") id: Int
    ): Call<Libro?>

    @POST("libros")
    fun insertLibro(
        @Body producto: Libro
    ): Call<Libro>

    @PUT("libros/{id}")
    fun updateLibro(
        @Body producto: Libro,
        @Path("id") id: Int
    ): Call<Libro>

    @DELETE("libros/{id}")
    fun deleteLibro(
        @Path("id") id: Int
    ): Call<Void>

    @GET("generos")
    fun getGeneros(): Call<Generos>

    @GET("generos/{id}")
    fun getGeneroById(
        @Path("id") id: Int
    ): Call<Genero?>

    @POST("generos")
    fun insertGenero(
        @Body producto: Genero
    ): Call<Genero>

    @PUT("generos/{id}")
    fun updateGenero(
        @Body producto: Genero,
        @Path("id") id: Int
    ): Call<Genero>

    @DELETE("generos/{id}")
    fun deleteGenero(
        @Path("id") id: Int
    ): Call<Void>


}