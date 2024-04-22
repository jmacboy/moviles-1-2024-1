package com.example.practicatouch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.pow
import kotlin.math.sqrt

class Lienzo(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var shape: Shape = Shape.NONE
    private var xOrigin = 0f
    private var yOrigin = 0f
    private var xDestination = 0f
    private var yDestination = 0f
    private val paint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 10f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        when (shape) {
            Shape.LINE -> canvas.drawLine(xOrigin, yOrigin, xDestination, yDestination, paint)
            Shape.RECTANGLE -> canvas.drawRect(xOrigin, yOrigin, xDestination, yDestination, paint)
            Shape.CIRCLE -> {
                val radius = sqrt(
                    (xDestination - xOrigin).toDouble().pow(2.0)
                            + (yDestination - yOrigin).toDouble()
                        .pow(2.0)
                ).toFloat()
                canvas.drawCircle(xOrigin, yOrigin, radius, paint)
            }

            else -> {}
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                xOrigin = event.x
                yOrigin = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                xDestination = event.x
                yDestination = event.y
                invalidate()
            }
        }
        return true
    }

    fun setShape(shape: Shape) {
        this.shape = shape
    }

    fun setColor(color: Int) {
        paint.color = color
    }
}