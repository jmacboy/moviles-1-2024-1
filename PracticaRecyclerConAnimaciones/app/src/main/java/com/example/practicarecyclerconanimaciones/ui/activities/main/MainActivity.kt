package com.example.practicarecyclerconanimaciones.ui.activities.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicarecyclerconanimaciones.R
import com.example.practicarecyclerconanimaciones.databinding.ActivityMainBinding
import com.example.practicarecyclerconanimaciones.models.Genero
import com.example.practicarecyclerconanimaciones.ui.activities.adapters.GeneroAdapter
import com.example.practicarecyclerconanimaciones.ui.activities.adapters.OnGeneroClickListener
import com.example.practicarecyclerconanimaciones.ui.activities.detail.DetailActivity

class MainActivity : AppCompatActivity(), OnGeneroClickListener {
    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()
    val updateResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val genero = result.data?.getSerializableExtra("genero") as Genero
            val adapter = binding.lstGeneros.adapter as GeneroAdapter
            adapter.updateGenero(genero)
        }
    }
    val insertResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val genero = result.data?.getSerializableExtra("genero") as Genero
                val adapter = binding.lstGeneros.adapter as GeneroAdapter
                adapter.addGenero(genero)
            }
        }

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
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.fabAddGenero.setOnClickListener {
            insertResultLauncher.launch(
                Intent(this, DetailActivity::class.java)
            )
        }
    }

    private fun setupRecyclerView() {
        binding.lstGeneros.apply {
            adapter = GeneroAdapter(
                arrayListOf(
                    Genero(
                        1,
                        "Rock",
                        "https://supertreinosapp.com.br/wp-content/uploads/2022/08/THE-ROCK-2-WIDE.jpeg"
                    ),
                    Genero(
                        2,
                        "Pop",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_zrIqk0CGhszvQXvjg6ebMlFEZK0yuY9UiVhjd--Sd8Kdjw7BzBdCVKaUxdJg2ledEcg&usqp=CAU"
                    ),
                    Genero(
                        3,
                        "Reggaeton",
                        "https://www.dakotapulse.com/wp-content/uploads/2020/02/2019-03-29_0-1553902817.jpg"
                    ),
                    Genero(
                        4,
                        "Electronica",
                        "https://www.electropolis.es/media/magefan_blog/2017/09/Psychiatric-Disorder-1-1024x768.jpg"
                    ),
                    Genero(
                        5,
                        "Rap",
                        "https://cdn.aaihs.org/wp-content/uploads/2023/08/shutterstock_517774291-scaled.jpg"
                    ),
                    Genero(6, "Metal", "https://cdn2.hubspot.net/hubfs/2317338/Metal-surfaces.jpg"),
                ),
                this@MainActivity
            )
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onGeneroClick(genero: Genero) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("genero", genero)
        updateResultLauncher.launch(intent)
    }

    override fun onDeleteClick(genero: Genero) {
        val adapter = binding.lstGeneros.adapter as GeneroAdapter
        adapter.removeGenero(genero)
    }
}