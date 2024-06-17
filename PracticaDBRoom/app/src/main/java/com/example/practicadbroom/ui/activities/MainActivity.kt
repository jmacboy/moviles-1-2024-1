package com.example.practicadbroom.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.practicadbroom.R
import com.example.practicadbroom.databinding.ActivityMainBinding
import com.example.practicadbroom.db.AppDatabase
import com.example.practicadbroom.models.Persona
import com.example.practicadbroom.ui.adapters.PersonaAdapter

class MainActivity : AppCompatActivity(), PersonaAdapter.OnPersonaClickListener {
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
        doInsert()
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        val personas = db.personaDao().getAll()
        (binding.rvPersonaList.adapter as PersonaAdapter).updateData(personas as ArrayList<Persona>)
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

    private fun doInsert() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        db.personaDao().insert(
            Persona(
                "Juan",
                "Perez",
                30,
                "CDMX",
                "1990-01-01"
            )
        )
    }

    override fun onPersonaClick(persona: Persona) {

    }

    override fun onPersonaDelete(persona: Persona) {
    }
}