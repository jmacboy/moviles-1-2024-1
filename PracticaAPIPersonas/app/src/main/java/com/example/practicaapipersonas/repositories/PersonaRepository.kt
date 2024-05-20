package com.example.practicaapipersonas.repositories

import android.widget.Toast
import com.example.practicaapipersonas.api.PersonaService
import com.example.practicaapipersonas.models.Personas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PersonaRepository {
    fun getPersonaList(success: (Personas?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://personas.jmacboy.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: PersonaService =
            retrofit.create(PersonaService::class.java)
        service.getPersonas().enqueue(object : Callback<Personas> {
            override fun onResponse(res: Call<Personas>, response: Response<Personas>) {
                val postList = response.body()
                success(postList)
            }
            override fun onFailure(res: Call<Personas>, t: Throwable) {
                failure(t)
            }
        })
    }
}