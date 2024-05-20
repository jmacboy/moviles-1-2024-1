package com.example.bejeweled.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import kotlin.math.abs

class Tablero {
    var rows = 9
    var cols = 8
    private var selectedRow = -1
    private var selectedCol = -1

    // matriz llena de números del 1 al 7
    var matriz: Array<Array<Jewel>> = Array(rows) { row ->
        Array(cols) { col ->
            Jewel(row = row, col = col)
        }
    }

    fun select(row: Int, col: Int) {
        selectedRow = row
        selectedCol = col
        matriz[row][col].selected = true
        printBoard()
    }

    fun move(row: Int, col: Int) {
        val difX = row - selectedRow
        val difY = col - selectedCol
        if (selectedRow != -1 && selectedCol != -1 && abs(difX) + abs(difY) == 1) {
            val jewel = matriz[selectedRow][selectedCol]
            jewel.selected = false
            matriz[selectedRow][selectedCol] = matriz[row][col].copy(row = selectedRow, col = selectedCol)
            matriz[row][col] = jewel.copy(row = row, col = col)
            selectedRow = -1
            selectedCol = -1
            printBoard()
        }
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

    fun hasSelection(): Boolean {
        return selectedRow != -1 && selectedCol != -1
    }

    fun validateMove(row: Int, col: Int) {
        if (selectedCol == col && selectedRow == row) {
            selectedRow = -1
            selectedCol = -1
            matriz[row][col].selected = false
            Log.d("Unselected", "x: $row, y: $col")
            return
        }
        if (hasSelection()) {
            Log.d("Moved", "x: $row, y: $col")
            move(row, col)
        } else {
            Log.d("Selected", "x: $row, y: $col")
            select(row, col)
        }
    }
}