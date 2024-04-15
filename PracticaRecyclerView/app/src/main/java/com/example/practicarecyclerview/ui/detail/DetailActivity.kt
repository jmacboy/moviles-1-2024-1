package com.example.practicarecyclerview.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.practicarecyclerview.R
import com.example.practicarecyclerview.models.Person

class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if (savedInstanceState == null) {
            val person = intent.getSerializableExtra("person") as Person?
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailFragment.newInstance(person))
                .commitNow()
        }
    }
}