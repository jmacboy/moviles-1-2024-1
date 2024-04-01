package com.example.practicamvvmform

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicamvvmform.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private val model: RegisterViewModel by viewModels()
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
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
        model.goToLoginScreen.observe(this) {
            if (it) {
               finish()
            }
        }
    }

    private fun setupEventListeners() {
        binding.btnCreateUser.setOnClickListener { doRegister() }
    }

    private fun doRegister() {
        val name = binding.txtFirstName.text.toString()
        val lastName = binding.txtLastName.text.toString()
        val username = binding.txtUserName.text.toString()
        val password = binding.txtRegisterPassword.text.toString()

        model.registerUser(name, lastName, username, password)
    }
}