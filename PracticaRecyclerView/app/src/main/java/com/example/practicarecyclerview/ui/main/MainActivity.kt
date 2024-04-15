package com.example.practicarecyclerview.ui.main

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
import com.example.practicarecyclerview.R
import com.example.practicarecyclerview.databinding.ActivityMainBinding
import com.example.practicarecyclerview.models.Person
import com.example.practicarecyclerview.ui.adapters.PersonAdapter
import com.example.practicarecyclerview.ui.detail.DetailActivity

class MainActivity : AppCompatActivity(), PersonAdapter.OnPersonClickListener {
    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()
    var updateLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            val person = data?.getSerializableExtra("person") as Person
            model.updatePerson(person)
        }
    }
    var insertLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            val person = data?.getSerializableExtra("person") as Person
            model.insertPerson(person)
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
        setupViewModelObservers()
        setupEventListeners()
        model.loadPeople()
    }

    private fun setupEventListeners() {
        binding.btnAddPerson.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            insertLauncher.launch(intent)
        }
    }

    private fun setupViewModelObservers() {
        model.personList.observe(this) {
            val adapter = binding.lstPeople.adapter as PersonAdapter
            adapter.clear()
            if (it != null) {
                adapter.addAll(it)
            }
        }
    }

    private fun setupRecyclerView() {

        val adapter = PersonAdapter(
            arrayListOf(),
            this
        )
        binding.lstPeople.adapter = adapter
        binding.lstPeople.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
    }

    override fun onPersonClick(person: Person) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("person", person)

        updateLauncher.launch(intent)
    }

    override fun onPersonDeleteClick(person: Person) {
        model.deletePerson(person)
    }
}