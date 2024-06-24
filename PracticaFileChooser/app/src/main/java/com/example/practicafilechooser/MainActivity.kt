package com.example.practicafilechooser

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafilechooser.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var fileChooserResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data

                val bitmap = getBitmapFromIntent(data)
                binding.imgSelected.setImageBitmap(bitmap)

            }
        }

    private fun getBitmapFromIntent(data: Intent?): Bitmap? {
        val imgUrl = data?.data

        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

        val cursor = contentResolver.query(
            imgUrl!!,
            filePathColumn, null, null, null
        )
        cursor!!.moveToFirst()

        val columnIndex = cursor.getColumnIndex(filePathColumn[0])
        val picturePath = cursor.getString(columnIndex)
        cursor.close()

        val bitmap = BitmapFactory.decodeFile(picturePath)
        return bitmap
    }


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
        binding.apply {
            btnOpenFileSelector.setOnClickListener { openFileSelector() }
        }
    }

    private fun openFileSelector() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        fileChooserResultLauncher.launch(intent)
    }
}