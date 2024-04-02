package com.example.practicamvvmform

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicamvvmform.adapters.UserArrayAdapter
import com.example.practicamvvmform.databinding.ActivityWelcomeBinding
import com.example.practicamvvmform.models.FakeDB

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private val model: WelcomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val user = intent.getStringExtra("user")
//        binding.lblWelcome.text = "Bienvenido $user"
        setupListView()
    }

    private fun setupListView() {
        val adapter = UserArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            FakeDB.users
        )
        binding.lstUsers.adapter = adapter
    }
}