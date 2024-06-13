package com.example.practico4solucion.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico4solucion.models.Libros
import com.example.practico4solucion.repositories.GeneroRepository

class BooksByGenreViewModel : ViewModel() {
    private val _bookList: MutableLiveData<Libros> by lazy {
        MutableLiveData<Libros>(Libros())
    }
    val libroList: LiveData<Libros> get() = _bookList

    fun fetchListaLibrosPorGenero(id: Int) {
        GeneroRepository.getGenero(id,
            success = { genero ->
                genero?.let {
                    _bookList.value = genero.libros
                }
            },
            failure = {
                it.printStackTrace()
            })
    }
}