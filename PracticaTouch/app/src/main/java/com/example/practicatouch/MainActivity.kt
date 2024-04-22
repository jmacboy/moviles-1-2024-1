package com.example.practicatouch

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicatouch.databinding.ActivityMainBinding

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
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnLine.setOnClickListener {
            binding.lienzo.setShape(Shape.LINE)
        }
        binding.btnRectangle.setOnClickListener {
            binding.lienzo.setShape(Shape.RECTANGLE)
        }
        binding.btnCircle.setOnClickListener {
            binding.lienzo.setShape(Shape.CIRCLE)
        }
        binding.btnBlack.setOnClickListener {
            binding.lienzo.setColor(Color.BLACK)
        }
        binding.btnBlue.setOnClickListener {
            binding.lienzo.setColor(Color.BLUE)
        }
        binding.btnRed.setOnClickListener {
            binding.lienzo.setColor(Color.RED)
        }
        binding.btnMagenta.setOnClickListener {
            binding.lienzo.setColor(Color.MAGENTA)
        }
    }
}