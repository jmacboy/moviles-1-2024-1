package com.example.practicarecyclerconanimaciones.ui.activities.detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicarecyclerconanimaciones.R
import com.example.practicarecyclerconanimaciones.databinding.ActivityDetailBinding
import com.example.practicarecyclerconanimaciones.models.Genero

class DetailActivity : AppCompatActivity() {
    private var generoId: Int = 0
    private lateinit var binding: ActivityDetailBinding
    private val model: DetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        loadGenero()
        setupEventlisteners()
    }

    private fun loadGenero() {
        val genero = intent.getSerializableExtra("genero") as Genero?
        if (genero != null) {
            binding.txtGeneroName.editText?.setText(genero.nombre)
            binding.txtGeneroUrl.editText?.setText(genero.imagenUrl)
            generoId = genero.id
        }
    }

    private fun setupEventlisteners() {
        binding.apply {
            btnSaveGenero.setOnClickListener { saveGenero() }
            btnCancelGenero.setOnClickListener { finish() }
        }
    }

    private fun saveGenero() {
        val nombre = binding.txtGeneroName.editText?.text.toString()
        val imagenUrl = binding.txtGeneroUrl.editText?.text.toString()
        val genero = Genero(
            id = generoId,
            nombre = nombre,
            imagenUrl = imagenUrl
        )
        val intentResult = Intent()
        intentResult.putExtra("genero", genero)
        setResult(RESULT_OK, intentResult)
        finish()
    }
}