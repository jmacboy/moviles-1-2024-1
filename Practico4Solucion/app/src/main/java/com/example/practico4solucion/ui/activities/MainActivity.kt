package com.example.practico4solucion.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practico4solucion.R
import com.example.practico4solucion.databinding.ActivityMainBinding
import com.example.practico4solucion.models.Libro
import com.example.practico4solucion.ui.adapters.BookAdapter
import com.example.practico4solucion.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), BookAdapter.OnLibroClickListener {
    lateinit var binding: ActivityMainBinding
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
        setupEventListeners()
        setupRecyclerView()
        setupViewModelListeners()
    }

    override fun onResume() {
        super.onResume()
        model.fetchListaLibros()
    }

    private fun setupViewModelListeners() {
        model.libroList.observe(this) {
            (binding.rvbookList.adapter as BookAdapter).updateData(it)
        }

    }

    private fun setupRecyclerView() {
        binding.rvbookList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = BookAdapter(
                arrayListOf(),
                this@MainActivity
            )
        }
    }

    private fun setupEventListeners() {
        binding.btnShowGenres.setOnClickListener {
            val intent = Intent(this, GenresActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onLibroClick(libro: Libro) {
    }

    override fun onLibroDelete(libro: Libro) {
    }
}