package com.example.practicamvvmform

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicamvvmform.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val model: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
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

        setupEventListeners()
        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        model.errorMessage.observe(this) {
            if (it.isEmpty()) {
                return@observe
            }
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        model.goToWelcomeScreen.observe(this) {
            if (it) {
                val user = binding.txtUser.text.toString()
                val intent = Intent(this, WelcomeActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }
        }
    }

    private fun setupEventListeners() {
        binding.btnSignIn.setOnClickListener {
            val user = binding.txtUser.text.toString()
            val password = binding.txtPassword.text.toString()
            model.signIn(user, password)
        }
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}