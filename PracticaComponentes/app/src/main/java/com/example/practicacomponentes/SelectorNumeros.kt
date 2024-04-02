package com.example.practicacomponentes

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class SelectorNumeros(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private var btnPlus: Button
    private var btnMinus: Button
    private var lblResult: TextView
    private var value = 0

    init {
        val container = inflate(context, R.layout.selector_numeros_layout, this)
        btnPlus = container.findViewById(R.id.btnPlus)
        btnMinus = container.findViewById(R.id.btnMinus)
        lblResult = container.findViewById(R.id.lblResult)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        btnPlus.setOnClickListener { addNumber() }
        btnMinus.setOnClickListener { substractNumber() }
    }

    private fun substractNumber() {
        value--
        reloadScreen()
    }

    private fun addNumber() {
        value++
        reloadScreen()
    }

    private fun reloadScreen() {
        lblResult.text = value.toString()
    }
}