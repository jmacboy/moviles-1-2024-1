package com.example.practicacalculadora

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicacalculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupEventListeners()
        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        model.result.observe(this) {
            if (it.isEmpty()) {
                binding.lblResult.text = "0"
                return@observe
            }
            binding.lblResult.text = it
        }
        model.showDivisionError.observe(this) {
            if (it) {
                binding.lblResult.text = "ERROR"
                Toast.makeText(this, "Error, division por cero", Toast.LENGTH_SHORT).show()
            }
        }
        model.enableMemoryButtons.observe(this) {
            binding.btnMr.isEnabled = it
            binding.btnMc.isEnabled = it
        }
    }

    private fun setupEventListeners() {
        binding.btn0.setOnClickListener { model.appendNumber(0) }
        binding.btn1.setOnClickListener { model.appendNumber(1) }
        binding.btn2.setOnClickListener { model.appendNumber(2) }
        binding.btn3.setOnClickListener { model.appendNumber(3) }
        binding.btn4.setOnClickListener { model.appendNumber(4) }
        binding.btn5.setOnClickListener { model.appendNumber(5) }
        binding.btn6.setOnClickListener { model.appendNumber(6) }
        binding.btn7.setOnClickListener { model.appendNumber(7) }
        binding.btn8.setOnClickListener { model.appendNumber(8) }
        binding.btn9.setOnClickListener { model.appendNumber(9) }
        binding.btnClearOne.setOnClickListener { model.clearOne() }
        binding.btnClearEverything.setOnClickListener { model.clearEverything() }
        binding.btnPlus.setOnClickListener { model.startOperation(OperationType.ADDITION) }
        binding.btnMinus.setOnClickListener { model.startOperation(OperationType.SUBTRACTION) }
        binding.btnMultiply.setOnClickListener { model.startOperation(OperationType.MULTIPLICATION) }
        binding.btnDivide.setOnClickListener { model.startOperation(OperationType.DIVISION) }
        binding.btnEquals.setOnClickListener { model.doOperation() }
        binding.btnMPlus.setOnClickListener { model.addToMemory() }
        binding.btnMMinus.setOnClickListener { model.subtractFromMemory() }
        binding.btnMr.setOnClickListener { model.readFromMemory() }
        binding.btnMc.setOnClickListener { model.clearMemory() }
    }

}