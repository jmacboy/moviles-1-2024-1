package com.example.practicaretrofit

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicaretrofit.api.JSONPlaceholderService
import com.example.practicaretrofit.databinding.ActivityMainBinding
import com.example.practicaretrofit.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
        binding.btnGetPostData.setOnClickListener {
            fetchPostInformation()
        }
    }

    private fun fetchPostInformation() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: JSONPlaceholderService =
            retrofit.create(JSONPlaceholderService::class.java)
        service.getPostById(1).enqueue(object : Callback<Post> {
            override fun onResponse(res: Call<Post>, response: Response<Post>) {
                val post = response.body()
                Toast.makeText(this@MainActivity, post?.title, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(res: Call<Post>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error al obtener datos", Toast.LENGTH_SHORT)
                    .show()
                t.printStackTrace()
            }

        })
    }
}