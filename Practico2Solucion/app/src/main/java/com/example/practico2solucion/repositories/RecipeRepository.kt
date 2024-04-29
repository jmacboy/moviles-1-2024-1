package com.example.practico2solucion.repositories

import com.example.practico2solucion.models.Ingredient
import com.example.practico2solucion.models.Recipe

object RecipeRepository {
    private val recipeList = arrayListOf<Recipe>(
        Recipe(
            "Tarta de jamón y queso",
            1,
            arrayListOf(1, 2, 3),
            "Tarta de jamón y queso",
        ),
        Recipe(
            "Tarta de verduras",
            2,
            arrayListOf(1, 2, 3),
            "Tarta de verduras",
        ),
        Recipe(
            "Tarta de atún",
            3,
            arrayListOf(1, 2, 3),
            "Tarta de atún",
        ),
        Recipe(
            "Tarta de pollo",
            4,
            arrayListOf(1, 2, 3),
            "Tarta de pollo",
        ),
        Recipe(
            "Tarta de choclo",
            5,
            arrayListOf(1, 2, 3),
            "Tarta de choclo",
        ),
        Recipe(
            "Tarta de cebolla",
            6,
            arrayListOf(1, 2, 3),
            "Tarta de cebolla",
        ),
        Recipe(
            "Tarta de espinaca",
            7,
            arrayListOf(1, 2, 3),
            "Tarta de espinaca",
        ),
    )

    fun getRecipes(): ArrayList<Recipe> {
        return recipeList;
    }

    fun getRecipeWithIngredients(ingredients: ArrayList<Ingredient>): ArrayList<Recipe> {
        return recipeList.filter {
            it.ingredients.containsAll(ingredients.map { it.id })
        } as ArrayList<Recipe>
    }
}