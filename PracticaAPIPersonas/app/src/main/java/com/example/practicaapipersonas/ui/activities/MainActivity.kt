package com.example.practicaapipersonas.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaapipersonas.R
import com.example.practicaapipersonas.databinding.ActivityMainBinding
import com.example.practicaapipersonas.models.Personas
import com.example.practicaapipersonas.repositories.PersonaRepository
import com.example.practicaapipersonas.ui.adapters.PersonaAdapter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
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
        fetchListaPersonas()
    }

    private fun fetchListaPersonas() {
        PersonaRepository.getPersonaList(
            success = { personas ->
                personas?.let {
                    val adapter = (binding.lstPersonas.adapter as PersonaAdapter)
                    adapter.updateData(it)
                }
            },
            failure = {
                it.printStackTrace()
            })

    }

    private fun setupRecyclerView() {
        binding.lstPersonas.apply {
            this.adapter = PersonaAdapter(Personas())
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}