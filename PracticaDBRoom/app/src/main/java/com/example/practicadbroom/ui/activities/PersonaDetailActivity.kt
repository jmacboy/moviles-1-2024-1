package com.example.practicadbroom.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicadbroom.R
import com.example.practicadbroom.databinding.ActivityPersonaDetailBinding
import com.example.practicadbroom.ui.viewmodels.PersonaDetailViewModel

class PersonaDetailActivity : AppCompatActivity() {
    private var id: Int = 0
    private lateinit var binding: ActivityPersonaDetailBinding
    private val model: PersonaDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPersonaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        loadPersona()
        setupViewModelObservers()
        setupEventlisteners()
    }

    private fun setupEventlisteners() {
        binding.btnSavePersona.setOnClickListener {
            model.savePersona(
                this,
                id,
                binding.txtPersonName.text.toString(),
                binding.txtPersonLastName.text.toString(),
                binding.txtPersonAge.text.toString().toInt(),
                binding.txtPersonCity.text.toString(),
                binding.txtPersonBirthDate.text.toString()
            )
            finish()
        }
    }

    private fun setupViewModelObservers() {
        model.persona.observe(this) {
            it?.let {
                binding.apply {
                    txtPersonName.setText(it.nombre)
                    txtPersonLastName.setText(it.apellido)
                    txtPersonAge.setText(it.edad.toString())
                    txtPersonCity.setText(it.ciudad)
                    txtPersonBirthDate.setText(it.fechaNacimiento)
                }
            }
        }
    }

    private fun loadPersona() {
        id = intent.getIntExtra("id", 0)
        if (id == 0) {
            return
        }
        model.loadPersona(this, id)
    }
}