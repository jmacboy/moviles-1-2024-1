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
import com.example.practico4solucion.databinding.ActivityGenerosBinding
import com.example.practico4solucion.models.Genero
import com.example.practico4solucion.ui.adapters.GenreAdapter
import com.example.practico4solucion.ui.viewmodels.GenresViewModel

class GenresActivity : AppCompatActivity(), GenreAdapter.OnGeneroClickListener {
    lateinit var binding: ActivityGenerosBinding
    private val model: GenresViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGenerosBinding.inflate(layoutInflater)
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
        model.fetchListaGeneros()
    }

    private fun setupViewModelListeners() {
        model.generolist.observe(this) {
            (binding.rvGenreList.adapter as GenreAdapter).updateData(it)
        }
    }

    private fun setupRecyclerView() {
        binding.rvGenreList.apply {
            layoutManager = LinearLayoutManager(this@GenresActivity)
            adapter = GenreAdapter(
                arrayListOf(),
                this@GenresActivity
            )
        }
    }

    private fun setupEventListeners() {

    }

    override fun onGeneroClick(genero: Genero) {
        val intent = Intent(this, BooksByGenreActivity::class.java)
        intent.putExtra("id", genero.id)
        startActivity(intent)
    }

    override fun onGeneroDelete(genero: Genero) {
    }
}