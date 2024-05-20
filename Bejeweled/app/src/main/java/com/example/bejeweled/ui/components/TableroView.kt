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
        if (event?.action == MotionEvent.ACTION_DOWN) {
            val col = event.x.toInt() / (width / model.cols)
            val row = event.y.toInt() / (height / model.rows)
            Log.d("Selected", "x: $row, y: $col")
            model.select(row, col)
//            invalidate()
        } else if (event?.action == MotionEvent.ACTION_UP) {
            val col = event.x.toInt() / (width / model.cols)
            val row = event.y.toInt() / (height / model.rows)
            Log.d("Moved", "x: $row, y: $col")
            model.move(row, col)
            invalidate()
        }

        return true
    }
}