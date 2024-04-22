package com.example.practicadibujos

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.View

class Lienzo(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint()
    private var circulos = arrayListOf<Point>()
    private var x = 50
    private var y = 50
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
/*//        paint.color = Color.BLUE
//        canvas.drawLine(0f, 0f, width.toFloat(), height.toFloat(), paint)
//        paint.color = Color.RED
//        canvas.drawRect(200f, 200f, 500f, 500f, paint)
//        paint.color = Color.RED
//        canvas.drawCircle(300f, 300f, 100f, paint)*/
        paint.color = Color.RED
        for (circ in circulos) {
            canvas.drawCircle(circ.x.toFloat(), circ.y.toFloat(), 50f, paint)
        }
    }

    fun addCircle() {
        circulos.add(Point(x, y))
        x += 100
        if (x > width) {
            x = 50
            y += 100
        }
        invalidate()
    }
}