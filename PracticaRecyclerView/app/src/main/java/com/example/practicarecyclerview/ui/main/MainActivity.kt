package com.example.practicarecyclerview.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicarecyclerview.R
import com.example.practicarecyclerview.databinding.ActivityMainBinding
import com.example.practicarecyclerview.models.Person
import com.example.practicarecyclerview.ui.adapters.PersonAdapter

class MainActivity : AppCompatActivity(), PersonAdapter.OnPersonClickListener {
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
    }

    private fun setupRecyclerView() {
        val adapter = PersonAdapter(
            mutableListOf(
                Person(
                    1,
                    "Juan",
                    "Perez",
                    12,
                    "juan@test.com",
                    "123456"
                ),
                Person(
                    2,
                    "Maria",
                    "Lopez",
                    22,
                    "maria@test.com",
                    "1234567"
                ),
                Person(
                    3,
                    "Pedro",
                    "Gomez",
                    32,
                    "pedro@test.com",
                    "12345678"
                )
            ),
            this
        )
        binding.lstPeople.adapter = adapter
        binding.lstPeople.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
    }

    override fun onPersonClick(person: Person) {
        Log.d("MainActivity", "Person clicked: ${person.name}")
        Toast.makeText(this, person.name, Toast.LENGTH_LONG).show()
    }
}