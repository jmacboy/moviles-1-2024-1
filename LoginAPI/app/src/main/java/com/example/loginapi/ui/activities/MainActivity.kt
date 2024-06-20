package com.example.loginapi.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginapi.R
import com.example.loginapi.databinding.ActivityMainBinding
import com.example.loginapi.models.dto.Restaurant
import com.example.loginapi.repositories.PreferencesRepository
import com.example.loginapi.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val model: MainViewModel by viewModels()
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
        checkToken()
        setupEventListeners()
        setupViewModelObservers()
    }

    private fun checkToken() {
        val token = PreferencesRepository.getToken(this)
        if (token != null) {
            Toast.makeText(this, "El token es: $token", Toast.LENGTH_SHORT).show()
        }


//        val restaurant = Restaurant(
//            name = "El restaurante de la esquina",
//            city = "San Salvador"
//        )
    }

    private fun setupEventListeners() {
        binding.btnSignIn.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()
            model.login(email, password, this)
        }
    }

    private fun setupViewModelObservers() {
        model.errorMessage.observe(this) {
            if (it.isNotEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}