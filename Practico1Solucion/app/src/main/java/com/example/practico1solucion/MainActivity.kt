package com.example.practico1solucion

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practico1solucion.databinding.ActivityMainBinding
import com.example.practico1solucion.ui.adapters.ImageAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
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
        setupViewPager()
    }

    private fun setupViewPager() {
        val images = listOf(
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3
        )
        val adapter = ImageAdapter(images)
        binding.storiesPager.adapter = adapter
        TabLayoutMediator(binding.storyTabs, binding.storiesPager) { tab, position -> }.attach() //The Magical Line
    }
}