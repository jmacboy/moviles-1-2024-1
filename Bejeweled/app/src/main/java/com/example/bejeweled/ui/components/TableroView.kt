package com.example.bejeweled.ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.bejeweled.model.Tablero

class TableroView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var model: Tablero = Tablero()
    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        model.draw(canvas, paint, width, height)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            val col = it.x.toInt() / (width / model.cols)
            val row = it.y.toInt() / (height / model.rows)
            when (it.action) {
                MotionEvent.ACTION_DOWN -> {
                    model.validateMove(row, col)
                    invalidate()
                }
            }
        }
        return true
    }
}