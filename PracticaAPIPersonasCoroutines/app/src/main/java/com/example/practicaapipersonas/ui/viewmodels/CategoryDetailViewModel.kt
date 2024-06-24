package com.example.practicaapipersonas.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practiacapipersonas.repositories.CategoryRepository
import com.example.practicaapipersonas.models.Categoria
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryDetailViewModel : ViewModel() {
    private val _closeActivity: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val closeActivity: LiveData<Boolean> get() = _closeActivity
    private val _category: MutableLiveData<Categoria?> by lazy {
        MutableLiveData<Categoria?>(null)
    }
    val category: LiveData<Categoria?> get() = _category

    private val _showLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val showLoading: LiveData<Boolean> get() = _showLoading

    fun saveCategory(nombre: String, id: Int) {
        val category = Categoria(
            nombre = nombre
        )
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.postValue(true)
            if (id != 0) {
                category.id = id
                CategoryRepository.updateCategory(category,
                    success = {
                        _closeActivity.postValue(true)
                        _showLoading.postValue(false)
                    },
                    failure = {
                        it.printStackTrace()
                        _showLoading.postValue(false)
                    })
            } else {
                CategoryRepository.insertCategory(category,
                    success = {
                        _closeActivity.postValue(true)
                        _showLoading.postValue(false)

                    },
                    failure = {
                        it.printStackTrace()
                        _showLoading.postValue(false)

                    })
            }
        }
    }

    fun loadCategory(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.postValue(true)

            CategoryRepository.getCategory(id,
                success = {
                    _category.postValue(it)
                    _showLoading.postValue(false)
                },
                failure = {
                    it.printStackTrace()
                    _showLoading.postValue(false)
                })
        }
    }
}