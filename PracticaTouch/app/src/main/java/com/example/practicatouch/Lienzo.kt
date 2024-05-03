package com.example.practicatouch

import android.content.Context
import android.graphics.Bitmap
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
    var bitmap: Bitmap? = null
    var lastStatus: Bitmap? = null
    var currentColor = Color.BLUE

    private val paint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 10f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.WHITE
//        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
        bitmap?.let {
            canvas.drawBitmap(it, 0f, 0f, null)
        }
        paint.color = currentColor
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
                lastStatus = bitmap?.copy(Bitmap.Config.ARGB_8888, true)
                xOrigin = event.x
                yOrigin = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                xDestination = event.x
                yDestination = event.y
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
                bitmap = getBitmapFromView(this)
                invalidate()
            }
        }
        return true
    }

    private fun getBitmapFromView(view: View): Bitmap {
        val bitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    fun setShape(shape: Shape) {
        this.shape = shape
    }

    fun setColor(color: Int) {
        paint.color = color
        currentColor = color
    }

    fun undo() {
        bitmap = lastStatus?.copy(Bitmap.Config.ARGB_8888, true)
        xOrigin = 0f
        yOrigin = 0f
        xDestination = 0f
        yDestination = 0f
        invalidate()
    }
}