package com.example.practiacapipersonas.repositories

import com.example.practicaapipersonas.api.APIProductosService
import com.example.practicaapipersonas.models.Producto
import com.example.practicaapipersonas.models.Productos
import com.example.practicaapipersonas.repositories.RetrofitRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ProductRepository {
    fun getProductList(success: (Productos?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APIProductosService =
            retrofit.create(APIProductosService::class.java)
        service.getProductos().enqueue(object : Callback<Productos> {
            override fun onResponse(res: Call<Productos>, response: Response<Productos>) {
                val postList = response.body()
                success(postList)
            }

            override fun onFailure(res: Call<Productos>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun insertProduct(
        product: Producto,
        success: (Producto) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APIProductosService =
            retrofit.create(APIProductosService::class.java)
        service.insertProducto(product).enqueue(object : Callback<Producto> {
            override fun onResponse(res: Call<Producto>, response: Response<Producto>) {
                val objProduct = response.body()
                success(objProduct!!)
            }

            override fun onFailure(res: Call<Producto>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun getProduct(id: Int, success: (Producto?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APIProductosService =
            retrofit.create(APIProductosService::class.java)
        service.getProductoById(id).enqueue(object : Callback<Producto?> {
            override fun onResponse(res: Call<Producto?>, response: Response<Producto?>) {
                success(response.body())
            }

            override fun onFailure(res: Call<Producto?>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun updateProduct(
        product: Producto,
        success: (Producto) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APIProductosService =
            retrofit.create(APIProductosService::class.java)
        val productId = product.id ?: 0
        service.updateProducto(product, productId).enqueue(object : Callback<Producto> {
            override fun onResponse(res: Call<Producto>, response: Response<Producto>) {
                val objProduct = response.body()!!
                success(objProduct)
            }

            override fun onFailure(res: Call<Producto>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun deleteProduct(
        id: Int,
        success: () -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APIProductosService =
            retrofit.create(APIProductosService::class.java)
        service.deleteProducto(id).enqueue(object : Callback<Void> {
            override fun onResponse(res: Call<Void>, response: Response<Void>) {
                success()
            }

            override fun onFailure(res: Call<Void>, t: Throwable) {
                failure(t)
            }
        })
    }
}