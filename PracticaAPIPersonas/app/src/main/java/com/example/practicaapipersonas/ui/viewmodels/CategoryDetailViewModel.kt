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

    fun saveCategory(nombre: String) {
        val category = Categoria(
            nombre = nombre
        )
        CategoryRepository.insertCategory(category,
            success = {
                _closeActivity.value = true
            },
            failure = {
                it.printStackTrace()
            })

    }
}