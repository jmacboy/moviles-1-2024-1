package com.example.practicaapipersonas.api

import com.example.practicaapipersonas.models.Personas
import retrofit2.Call
import retrofit2.http.GET

interface PersonaService {
    @GET("personas")
    fun getPersonas(): Call<Personas>
}