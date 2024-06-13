package com.example.practico4solucion.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practico4solucion.R
import com.example.practico4solucion.databinding.ActivityBooksByGenreBinding
import com.example.practico4solucion.models.Libro
import com.example.practico4solucion.ui.adapters.BookAdapter
import com.example.practico4solucion.ui.viewmodels.BooksByGenreViewModel

class BooksByGenreActivity : AppCompatActivity(), BookAdapter.OnLibroClickListener {
    lateinit var binding: ActivityBooksByGenreBinding
    private val model: BooksByGenreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBooksByGenreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        setupRecyclerView()
        setupViewModelListeners()
        val id = intent.getIntExtra("id", 0)
        if(id != 0) {
            model.fetchListaLibrosPorGenero(id)
        }
    }

    private fun setupViewModelListeners() {
        model.libroList.observe(this) {
            (binding.rvBooksByGenreList.adapter as BookAdapter).updateData(it)
        }
    }

    private fun setupRecyclerView() {
        binding.rvBooksByGenreList.apply {
            layoutManager = LinearLayoutManager(this@BooksByGenreActivity)
            adapter = BookAdapter(
                arrayListOf(),
                this@BooksByGenreActivity
            )
        }
    }

    private fun setupEventListeners() {

    }

    override fun onLibroClick(libro: Libro) {
    }

    override fun onLibroDelete(libro: Libro) {
    }
}