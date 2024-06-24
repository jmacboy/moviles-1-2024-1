package com.example.practicaapipersonas.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practiacapipersonas.repositories.CategoryRepository
import com.example.practicaapipersonas.models.Categorias
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _categoryList: MutableLiveData<Categorias> by lazy {
        MutableLiveData<Categorias>(Categorias())
    }
    val categoryList: LiveData<Categorias> get() = _categoryList
    private val _showLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val showLoading: LiveData<Boolean> get() = _showLoading

    fun fetchListaPersonas() {
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.postValue(true)
//            Thread.sleep(5000) //para mostrar el loading
            CategoryRepository.getCategoryList(
                success = { personas ->
                    personas?.let {
                        _categoryList.postValue(it)
                        _showLoading.postValue(false)
                    }
                },
                failure = {
                    it.printStackTrace()
                    _showLoading.postValue(false)
                })
        }
    }

    fun deleteCategory(id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.postValue(true)

            CategoryRepository.deleteCategory(id!!,
                success = {
                    fetchListaPersonas()
                },
                failure = {
                    it.printStackTrace()
                    _showLoading.postValue(false)
                })
        }
    }
}