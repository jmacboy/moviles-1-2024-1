package com.example.practicaapipersonas.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaapipersonas.R
import com.example.practicaapipersonas.databinding.ActivityProductoListBinding
import com.example.practicaapipersonas.ui.adapters.ProductoAdapter
import com.example.practicaapipersonas.ui.viewmodels.ProductoListViewModel

class ProductoListActivity : AppCompatActivity(), ProductoAdapter.OnProductoClickListener {
    lateinit var binding: ActivityProductoListBinding
    private val model: ProductoListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
        setupViewModelObservers()
    }

    private fun setupRecyclerView() {
        binding.lstProducts.apply {
            layoutManager = LinearLayoutManager(this@ProductoListActivity)
            adapter = ProductoAdapter(arrayListOf(), this@ProductoListActivity)
        }
    }

    private fun setupViewModelObservers() {
        model.productList.observe(this) {
            binding.lstProducts.adapter = ProductoAdapter(it, this)
        }
    }

    override fun onResume() {
        super.onResume()
        model.fetchListaProductos()
    }
}