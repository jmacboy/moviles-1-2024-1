package com.example.practico4solucion.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitRepository {
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://apilibreria.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}