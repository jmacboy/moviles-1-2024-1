package com.example.loginapi.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitRepository {
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://restaurantes.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}