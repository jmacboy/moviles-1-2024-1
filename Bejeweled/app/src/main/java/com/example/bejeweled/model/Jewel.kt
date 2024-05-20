package com.example.bejeweled.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

data class Jewel(
    var color: Int = getRandomColor(),
    var row: Int = 0,
    var col: Int = 0,
    var type: JewelType = JewelType.NORMAL,
    var selected: Boolean = false
) {
    fun paint(canvas: Canvas, paint: Paint, width: Int, height: Int) {
        val color = when (color) {
            1 -> Color.BLACK
            2 -> Color.MAGENTA
            3 -> Color.RED
            4 -> Color.GREEN
            5 -> Color.BLUE
            6 -> Color.YELLOW
            7 -> Color.CYAN
            else -> Color.WHITE
        }
        paint.color = color
        canvas.drawRect(
            (col * width + 1).toFloat(),
            (row * height + 1).toFloat(),
            ((col + 1) * width - 1).toFloat(),
            ((row + 1) * height - 1).toFloat(),
            paint
        )
        if (selected) {
            paint.color = Color.WHITE
//            paint.style = Paint.Style.STROKE
            canvas.drawRect(
                (col * width + 1).toFloat(),
                (row * height + 1).toFloat(),
                ((col + 1) * width - 1).toFloat(),
                ((row + 1) * height - 1).toFloat(),
                paint
            )
//            paint.style = Paint.Style.FILL
        }
    }

    companion object { //para métodos y variables estáticas
        fun getRandomColor(): Int {
            return (1..7).random()
        }
    }
}


