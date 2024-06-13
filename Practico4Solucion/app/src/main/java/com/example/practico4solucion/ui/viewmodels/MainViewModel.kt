package com.example.practico4solucion.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico4solucion.models.Libro
import com.example.practico4solucion.models.Libros
import com.example.practico4solucion.repositories.LibroRepository

class MainViewModel : ViewModel() {
    private val _bookList: MutableLiveData<Libros> by lazy {
        MutableLiveData<Libros>(Libros())
    }
    val libroList: LiveData<Libros> get() = _bookList

    fun fetchListaLibros() {
        LibroRepository.getLibroList(
            success = { libros ->
                libros?.let {
                    val libroSorted = it.sortedByDescending { libro -> libro.calificacion }
                    val libroList = Libros()
                    libroList.addAll(libroSorted)
                    _bookList.value = libroList
                }
            },
            failure = {
                it.printStackTrace()
            })
    }
}