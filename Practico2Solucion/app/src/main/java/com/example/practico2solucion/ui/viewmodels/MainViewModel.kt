package com.example.practico2solucion.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico2solucion.models.Ingredient
import com.example.practico2solucion.repositories.IngredientRepository

class MainViewModel : ViewModel() {


    private val _ingredientList: MutableLiveData<ArrayList<Ingredient>> by lazy {
        MutableLiveData<ArrayList<Ingredient>>(null)
    }
    val ingredientList: LiveData<ArrayList<Ingredient>> get() = _ingredientList
    fun loadIngredients() {
        _ingredientList.value = IngredientRepository.getIngredientList()
    }

    fun selectIngredient(ingredient: Ingredient) {
        val list = _ingredientList.value
        val index = list?.indexOfFirst { it.id == ingredient.id }
        if (index != null) {
            list[index].selected = !list[index].selected
        }
        _ingredientList.value = list
    }

    fun getSelectedIngredients(): ArrayList<Ingredient> {
        return _ingredientList.value?.filter { it.selected } as ArrayList<Ingredient>
    }
}