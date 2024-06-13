package com.example.practico4solucion.repositories

import com.example.practico4solucion.api.APILibrosService
import com.example.practico4solucion.models.Libro
import com.example.practico4solucion.models.Libros
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LibroRepository {
    fun getLibroList(success: (Libros?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibrosService =
            retrofit.create(APILibrosService::class.java)
        service.getLibros().enqueue(object : Callback<Libros> {
            override fun onResponse(res: Call<Libros>, response: Response<Libros>) {
                val postList = response.body()
                success(postList)
            }

            override fun onFailure(res: Call<Libros>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun insertLibro(
        libro: Libro,
        success: (Libro) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibrosService =
            retrofit.create(APILibrosService::class.java)
        service.insertLibro(libro).enqueue(object : Callback<Libro> {
            override fun onResponse(res: Call<Libro>, response: Response<Libro>) {
                val objLibro = response.body()
                success(objLibro!!)
            }

            override fun onFailure(res: Call<Libro>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun getLibro(id: Int, success: (Libro?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibrosService =
            retrofit.create(APILibrosService::class.java)
        service.getLibroById(id).enqueue(object : Callback<Libro?> {
            override fun onResponse(res: Call<Libro?>, response: Response<Libro?>) {
                success(response.body())
            }

            override fun onFailure(res: Call<Libro?>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun updateLibro(
        libro: Libro,
        success: (Libro) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibrosService =
            retrofit.create(APILibrosService::class.java)
        val libroId = libro.id ?: 0
        service.updateLibro(libro, libroId).enqueue(object : Callback<Libro> {
            override fun onResponse(res: Call<Libro>, response: Response<Libro>) {
                val objLibro = response.body()!!
                success(objLibro)
            }

            override fun onFailure(res: Call<Libro>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun deleteLibro(
        id: Int,
        success: () -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibrosService =
            retrofit.create(APILibrosService::class.java)
        service.deleteLibro(id).enqueue(object : Callback<Void> {
            override fun onResponse(res: Call<Void>, response: Response<Void>) {
                success()
            }

            override fun onFailure(res: Call<Void>, t: Throwable) {
                failure(t)
            }
        })
    }
}