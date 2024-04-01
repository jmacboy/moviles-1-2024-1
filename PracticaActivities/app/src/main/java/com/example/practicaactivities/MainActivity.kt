package com.example.practicaactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnGoTosecondActivity: Button
    private lateinit var btnGoToThirdActivity: Button
    private lateinit var txtName: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnGoTosecondActivity = findViewById(R.id.btnGoToSecondActivity)
        btnGoToThirdActivity = findViewById(R.id.btnGoToThirdActivity)
        txtName = findViewById(R.id.txtName)

        btnGoTosecondActivity.setOnClickListener { goToSecondActivity() }
        btnGoToThirdActivity.setOnClickListener { goToThirdActivity() }
    }

    private fun goToThirdActivity() {
        val name = txtName.text.toString()
        val intent = Intent(this, TercerAcitivity::class.java)
        intent.putExtra("varName", name)
        startActivity(intent)
    }

    private fun goToSecondActivity() {
        val intent = Intent(this, SegundoActivity::class.java)
        startActivity(intent)
    }
}