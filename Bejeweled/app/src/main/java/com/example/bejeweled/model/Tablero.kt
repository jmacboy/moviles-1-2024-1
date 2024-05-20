package com.example.bejeweled.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.math.abs

class Tablero {
    var rows = 9
    var cols = 8
    private var selectedRow = -1
    private var selectedCol = -1

    // matriz llena de números del 1 al 7
    var matriz: Array<Array<Jewel>> = arrayOf(
        getJewelCol(0),
        getJewelCol(1),
        getJewelCol(2),
        getJewelCol(3),
        getJewelCol(4),
        getJewelCol(5),
        getJewelCol(6),
        getJewelCol(7),
        getJewelCol(8),
    )

    private fun getJewelCol(col: Int): Array<Jewel> {
        return arrayOf(
            Jewel(row = 0, col = col),
            Jewel(row = 1, col = col),
            Jewel(row = 2, col = col),
            Jewel(row = 3, col = col),
            Jewel(row = 4, col = col),
            Jewel(row = 5, col = col),
            Jewel(row = 6, col = col),
            Jewel(row = 7, col = col)
        )
    }

    fun select(row: Int, col: Int) {
        selectedRow = row
        selectedCol = col
        printBoard()
    }

    fun move(row: Int, col: Int) {
        val difX = row - selectedRow
        val difY = col - selectedCol
        if (selectedRow != -1 && selectedCol != -1 && abs(difX) + abs(difY) == 1) {
            val jewel = matriz[selectedRow][selectedCol]
            matriz[selectedRow][selectedCol] = matriz[row][col]
            matriz[row][col] = jewel
            selectedRow = -1
            selectedCol = -1
        }
        printBoard()
    }

    fun printBoard() {
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                print("${matriz[i][j].color} ")
            }
            println()
        }
    }

    fun draw(canvas: Canvas, paint: Paint, width: Int, height: Int) {
        paint.color = Color.WHITE
        canvas.drawRect(
            0f, 0f, width.toFloat(), height.toFloat(), paint
        )
        val ancho = width / cols
        val alto = height / rows
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                val jewel = matriz[row][col]
                jewel.paint(canvas, paint, ancho, alto)
            }
        }
        println("Dibujando tablero...")
        printBoard()
        println("Tablero dibujado...")
    }
}