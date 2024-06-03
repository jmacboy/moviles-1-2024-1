package com.example.practicaapipersonas.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practiacapipersonas.repositories.CategoryRepository
import com.example.practicaapipersonas.models.Categoria

class CategoryDetailViewModel : ViewModel() {
    private val _closeActivity: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val closeActivity: LiveData<Boolean> get() = _closeActivity
    private val _category: MutableLiveData<Categoria?> by lazy {
        MutableLiveData<Categoria?>(null)
    }
    val category: LiveData<Categoria?> get() = _category

    fun saveCategory(nombre: String, id: Int) {
        val category = Categoria(
            nombre = nombre
        )
        if (id != 0) {
            category.id = id
            CategoryRepository.updateCategory(category,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        } else {
            CategoryRepository.insertCategory(category,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        }
    }

    fun loadCategory(id: Int) {
        CategoryRepository.getCategory(id,
            success = {
                _category.value = it
            },
            failure = {
                it.printStackTrace()
            })
    }
}