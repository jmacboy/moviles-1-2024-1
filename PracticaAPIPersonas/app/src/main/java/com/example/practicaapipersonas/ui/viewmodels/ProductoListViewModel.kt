package com.example.practicaapipersonas.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practiacapipersonas.repositories.ProductRepository
import com.example.practicaapipersonas.models.Productos

class ProductoListViewModel : ViewModel() {
    private val _productList: MutableLiveData<Productos> by lazy {
        MutableLiveData<Productos>(Productos())
    }
    val productList: LiveData<Productos> get() = _productList

    fun fetchListaProductos() {
        ProductRepository.getProductList(
            success = { productos ->
                productos?.let {
                    _productList.value = it
                }
            },
            failure = {
                it.printStackTrace()
            }
        )
    }
}