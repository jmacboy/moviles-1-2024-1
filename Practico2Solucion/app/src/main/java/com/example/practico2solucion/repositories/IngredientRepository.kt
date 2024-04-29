package com.example.practico2solucion.repositories

import com.example.practico2solucion.models.Ingredient

object IngredientRepository {
    fun getIngredientList(): ArrayList<Ingredient> {
        return arrayListOf(
            Ingredient(
                "Tomate",
                1,
                false,
                "https://www.frutas-hortalizas.com/img/frutas-hortalizas/tomate/tomate.jpg"
            ),
            Ingredient(
                "Lechuga",
                2,
                false,
                "https://www.frutas-hortalizas.com/img/frutas-hortalizas/lechuga/lechuga.jpg"
            ),
            Ingredient(
                "Zanahoria",
                3,
                false,
                "https://www.frutas-hortalizas.com/img/frutas-hortalizas/zanahoria/zanahoria.jpg"
            ),
            Ingredient(
                "Papa",
                4,
                false,
                "https://www.frutas-hortalizas.com/img/frutas-hortalizas/papa/papa.jpg"
            ),
            Ingredient(
                "Cebolla",
                5,
                false,
                "https://www.frutas-hortalizas.com/img/frutas-hortalizas/cebolla/cebolla.jpg"
            ),
            Ingredient(
                "Pimiento",
                6,
                false,
                "https://www.frutas-hortalizas.com/img/frutas-hortalizas/pimiento/pimiento.jpg"
            ),
            Ingredient(
                "Pepino",
                7,
                false,
                "https://www.frutas-hortalizas.com/img/frutas-hortalizas/pepino/pepino.jpg"
            ),
            Ingredient(
                "Ajo",
                8,
                false,
                "https://www.frutas-hortalizas.com/img/frutas-hortalizas/ajo/ajo.jpg"
            ),
            Ingredient(
                "Espinaca",
                9,
                false,
                "https://www.frutas-hortalizas.com/img/frutas-hortalizas/espinaca/espinaca.jpg"
            ),
            Ingredient(
                "Repollo",
                10,
                false,
                "https://www.frutas-hortalizas.com/img/frutas-hortalizas/repollo/repollo.jpg"
            )
        )
    }
}