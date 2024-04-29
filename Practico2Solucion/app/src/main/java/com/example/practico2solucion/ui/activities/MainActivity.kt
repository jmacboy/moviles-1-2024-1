package com.example.practico2solucion.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practico2solucion.R
import com.example.practico2solucion.databinding.ActivityMainBinding
import com.example.practico2solucion.models.Ingredient
import com.example.practico2solucion.repositories.RecipeRepository.getRecipeWithIngredients
import com.example.practico2solucion.ui.adapters.IngredientsAdapter
import com.example.practico2solucion.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), IngredientsAdapter.OnIngredientClickListener {
    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
        setupViewModel()
        setupEventListeners()
        model.loadIngredients()
    }

    private fun setupRecyclerView() {
        binding.lstIngredients.apply {
            adapter = IngredientsAdapter(arrayListOf(), this@MainActivity)
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
    }

    private fun setupViewModel() {
        model.ingredientList.observe(this) {
            val adapter = IngredientsAdapter(it, this)
            binding.lstIngredients.adapter = adapter
        }
    }

    private fun setupEventListeners() {
        binding.btnViewRecipes.setOnClickListener{
            val selectedIngredients = model.getSelectedIngredients()
            val recipes = getRecipeWithIngredients(selectedIngredients)
        }
    }

    override fun onIngredientClick(ingredient: Ingredient) {
        model.selectIngredient(ingredient)
    }
}