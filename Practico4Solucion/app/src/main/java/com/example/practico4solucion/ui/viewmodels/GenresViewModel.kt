package com.example.practico4solucion.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico4solucion.models.Generos
import com.example.practico4solucion.repositories.GeneroRepository

class GenresViewModel : ViewModel() {
    private val _genreList: MutableLiveData<Generos> by lazy {
        MutableLiveData<Generos>(Generos())
    }
    val generolist: LiveData<Generos> get() = _genreList

    fun fetchListaGeneros() {
        GeneroRepository.getGeneroList(
            success = { generos ->
                generos?.let {
                    _genreList.value = generos
                }
            },
            failure = {
                it.printStackTrace()
            })
    }
}