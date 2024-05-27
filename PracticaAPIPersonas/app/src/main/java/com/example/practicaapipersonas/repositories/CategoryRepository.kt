package com.example.practiacapipersonas.repositories

import com.example.practicaapipersonas.api.APIProductosService
import com.example.practicaapipersonas.models.Categoria
import com.example.practicaapipersonas.models.Categorias
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CategoryRepository {
    fun getCategoryList(success: (Categorias?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://practico4moviles.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: APIProductosService =
            retrofit.create(APIProductosService::class.java)
        service.getCategorias().enqueue(object : Callback<Categorias> {
            override fun onResponse(res: Call<Categorias>, response: Response<Categorias>) {
                val postList = response.body()
                success(postList)
            }

            override fun onFailure(res: Call<Categorias>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun insertCategory(category: Categoria, success:  (Categoria) -> Unit, failure:  (Throwable) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://practico4moviles.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: APIProductosService =
            retrofit.create(APIProductosService::class.java)
        service.insertCategoria(category).enqueue(object : Callback<Categoria> {
            override fun onResponse(res: Call<Categoria>, response: Response<Categoria>) {
                val objCategory = response.body()
                success(objCategory!!)
            }

            override fun onFailure(res: Call<Categoria>, t: Throwable) {
                failure(t)
            }
        })
    }
}