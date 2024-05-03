package com.example.practicadibujotablero.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.practicadibujotablero.R
import com.example.practicadibujotablero.ui.model.Tablero

class TableroView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var model: Tablero = Tablero()
    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }
    private var marioImg: Bitmap
    init {
        marioImg = BitmapFactory.decodeResource(context?.resources,
            R.drawable.mario);
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val board = model
        val ancho = width / board.columnas
        val alto = height / board.filas
        for (i in 0 until board.filas) {
            for (j in 0 until board.columnas) {
                val color = when (board.matriz[i][j]) {
                    1 -> Color.BLACK
                    2 -> Color.WHITE
                    3 -> Color.RED
                    4 -> Color.GREEN
                    5 -> Color.BLUE
                    6 -> Color.YELLOW
                    else -> Color.BLACK
                }
                paint.color = color
                if (board.matriz[i][j] == 7) {
                    canvas.drawBitmap(
                        marioImg,
                        (j * ancho).toFloat(),
                        (i * alto).toFloat(),
                        paint
                    )
                } else {
                    canvas.drawRect(
                        (j * ancho).toFloat(),
                        (i * alto).toFloat(),
                        ((j + 1) * ancho).toFloat(),
                        ((i + 1) * alto).toFloat(),
                        paint
                    )
                }
            }
        }
    }
}