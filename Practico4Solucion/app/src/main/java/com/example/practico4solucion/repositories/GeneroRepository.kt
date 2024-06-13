package com.example.practico4solucion.repositories

import com.example.practico4solucion.api.APILibrosService
import com.example.practico4solucion.models.Genero
import com.example.practico4solucion.models.Generos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GeneroRepository {
    fun getGeneroList(success: (Generos?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibrosService =
            retrofit.create(APILibrosService::class.java)
        service.getGeneros().enqueue(object : Callback<Generos> {
            override fun onResponse(res: Call<Generos>, response: Response<Generos>) {
                val postList = response.body()
                success(postList)
            }

            override fun onFailure(res: Call<Generos>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun insertGenero(
        libro: Genero,
        success: (Genero) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibrosService =
            retrofit.create(APILibrosService::class.java)
        service.insertGenero(libro).enqueue(object : Callback<Genero> {
            override fun onResponse(res: Call<Genero>, response: Response<Genero>) {
                val objGenero = response.body()
                success(objGenero!!)
            }

            override fun onFailure(res: Call<Genero>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun getGenero(id: Int, success: (Genero?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibrosService =
            retrofit.create(APILibrosService::class.java)
        service.getGeneroById(id).enqueue(object : Callback<Genero?> {
            override fun onResponse(res: Call<Genero?>, response: Response<Genero?>) {
                success(response.body())
            }

            override fun onFailure(res: Call<Genero?>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun updateGenero(
        libro: Genero,
        success: (Genero) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibrosService =
            retrofit.create(APILibrosService::class.java)
        val libroId = libro.id ?: 0
        service.updateGenero(libro, libroId).enqueue(object : Callback<Genero> {
            override fun onResponse(res: Call<Genero>, response: Response<Genero>) {
                val objGenero = response.body()!!
                success(objGenero)
            }

            override fun onFailure(res: Call<Genero>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun deleteGenero(
        id: Int,
        success: () -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibrosService =
            retrofit.create(APILibrosService::class.java)
        service.deleteGenero(id).enqueue(object : Callback<Void> {
            override fun onResponse(res: Call<Void>, response: Response<Void>) {
                success()
            }

            override fun onFailure(res: Call<Void>, t: Throwable) {
                failure(t)
            }
        })
    }
}