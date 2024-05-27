package com.example.practicaretrofit

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaretrofit.api.JSONPlaceholderService
import com.example.practicaretrofit.databinding.ActivityMainBinding
import com.example.practicaretrofit.models.Post
import com.example.practicaretrofit.ui.adapters.PostListAdapter
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
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = PostListAdapter(arrayListOf())
        binding.lstPosts.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setupEventListeners() {
        binding.btnGetPostData.setOnClickListener { fetchPostInformation() }
        binding.btnGetPostList.setOnClickListener { fetchPostList() }
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


    private fun fetchPostList() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: JSONPlaceholderService =
            retrofit.create(JSONPlaceholderService::class.java)
        service.getPostList().enqueue(object : Callback<List<Post>> {
            override fun onResponse(res: Call<List<Post>>, response: Response<List<Post>>) {
                val postList = response.body()
                Toast.makeText(this@MainActivity, postList?.size.toString(), Toast.LENGTH_SHORT)
                    .show()
                val adapter = binding.lstPosts.adapter as PostListAdapter
                adapter.updateData(postList)
            }

            override fun onFailure(res: Call<List<Post>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error al obtener datos", Toast.LENGTH_SHORT)
                    .show()
                t.printStackTrace()
            }

        })
    }
}