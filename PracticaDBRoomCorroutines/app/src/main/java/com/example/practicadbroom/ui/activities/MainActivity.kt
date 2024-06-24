package com.example.practicadbroom.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicadbroom.R
import com.example.practicadbroom.databinding.ActivityMainBinding
import com.example.practicadbroom.models.Persona
import com.example.practicadbroom.repositories.PersonaRepository
import com.example.practicadbroom.ui.adapters.PersonaAdapter
import com.example.practicadbroom.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), PersonaAdapter.OnPersonaClickListener {
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
        setupEventListeners()
        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        model.personas.observe(this) {
            it?.let {
                (binding.rvPersonaList.adapter as PersonaAdapter).updateData(it as ArrayList<Persona>)
            }
        }
    }

    private fun setupEventListeners() {
        binding.btnAddPersona.setOnClickListener {
            val intent = Intent(this, PersonaDetailActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        model.loadPersonas(this)
    }

    private fun setupRecyclerView() {
        binding.rvPersonaList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PersonaAdapter(
                arrayListOf(),
                this@MainActivity
            )
        }
    }

    override fun onPersonaClick(persona: Persona) {
        val intent = Intent(this, PersonaDetailActivity::class.java)
        intent.putExtra("id", persona.id)
        startActivity(intent)
    }

    override fun onPersonaDelete(persona: Persona) {
        model.deletePersona(this, persona)
    }
}