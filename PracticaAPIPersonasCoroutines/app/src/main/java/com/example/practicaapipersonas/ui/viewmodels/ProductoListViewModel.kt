package com.example.practicaapipersonas.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practiacapipersonas.repositories.ProductRepository
import com.example.practicaapipersonas.models.Productos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductoListViewModel : ViewModel() {
    private val _productList: MutableLiveData<Productos> by lazy {
        MutableLiveData<Productos>(Productos())
    }
    val productList: LiveData<Productos> get() = _productList

    fun fetchListaProductos() {
        viewModelScope.launch(Dispatchers.IO) {

            ProductRepository.getProductList(
                success = { productos ->
                    productos?.let {
                        _productList.postValue(it)
                    }
                },
                failure = {
                    it.printStackTrace()
                }
            )
        }
    }
}