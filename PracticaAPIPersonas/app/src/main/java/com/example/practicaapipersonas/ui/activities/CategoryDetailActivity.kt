package com.example.practicaapipersonas.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicaapipersonas.R
import com.example.practicaapipersonas.databinding.ActivityCategoryDetailBinding
import com.example.practicaapipersonas.ui.viewmodels.CategoryDetailViewModel

class CategoryDetailActivity : AppCompatActivity() {
    private var id: Int = 0
    lateinit var binding: ActivityCategoryDetailBinding
    private val model: CategoryDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        setupViewModelObservers()

        id = intent.getIntExtra("categoriaId", -1)
        if (id != -1) {
            model.loadCategory(id)
        }
    }

    private fun setupViewModelObservers() {
        model.closeActivity.observe(this) {
            if (it) {
                finish()
            }
        }
        model.category.observe(this) {
            if (it == null) {
                return@observe
            }
            binding.txtCategoryName.editText?.setText(it.nombre)
        }
    }

    private fun setupEventListeners() {
        binding.btnSaveCategory.setOnClickListener {
            model.saveCategory(binding.txtCategoryName.editText?.text.toString(), id)
        }
    }
}