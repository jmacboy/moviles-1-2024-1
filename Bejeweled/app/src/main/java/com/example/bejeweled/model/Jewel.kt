package com.example.bejeweled.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Jewel(
    var color: Int = getRandomColor(),
    var row: Int = 0,
    var col: Int = 0,
    var type: JewelType = JewelType.NORMAL
) {
    fun paint(canvas: Canvas, paint: Paint, ancho: Int, alto: Int) {
        val color = when (color) {
            1 -> Color.BLACK
            2 -> Color.MAGENTA
            3 -> Color.RED
            4 -> Color.GREEN
            5 -> Color.BLUE
            6 -> Color.YELLOW
            else -> Color.BLACK
        }
        paint.color = color
        canvas.drawRect(
            (row * ancho + 1).toFloat(),
            (col * alto + 1).toFloat(),
            ((row + 1) * ancho - 1).toFloat(),
            ((col + 1) * alto - 1).toFloat(),
            paint
        )
    }

    companion object { //para métodos y variables estáticas
        fun getRandomColor(): Int {
            return (1..7).random()
        }
    }
}


