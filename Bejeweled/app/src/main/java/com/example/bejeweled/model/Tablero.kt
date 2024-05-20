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

    init {
        verifyBoard()
    }

    fun select(row: Int, col: Int) {
        selectedRow = row
        selectedCol = col
        matriz[row][col].selected = true
        printBoard()
    }

    fun move(row: Int, col: Int): Boolean {
        val difX = row - selectedRow
        val difY = col - selectedCol
        if (selectedRow != -1 && selectedCol != -1 && abs(difX) + abs(difY) == 1) {
            val jewel = matriz[selectedRow][selectedCol]
            jewel.selected = false
            matriz[selectedRow][selectedCol] =
                matriz[row][col].copy(row = selectedRow, col = selectedCol)
            matriz[row][col] = jewel.copy(row = row, col = col)
            printBoard()
            return true
        }
        return false
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
            Log.d("Unselected", "x: $row, y: $col")
            clearSelection()
            return
        }
        if (hasSelection()) {
            Log.d("Moved", "x: $row, y: $col")
            val moved = move(row, col)
            if (!moved) {
                return
            }
            if (!hasPossibleLine(row, col, matriz[row][col].color)) {
                undoMove(row, col)
            }
            clearSelection()
            verifyBoard()
        } else {
            Log.d("Selected", "x: $row, y: $col")
            select(row, col)
        }
    }

    private fun verifyBoard() {
        while (verifyBoardHasLines()) {
            removeBoardLines()
            cascade()
            fillEmptyColors()
        }
    }

    private fun fillEmptyColors() {
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                if (matriz[row][col].color == 0) {
                    matriz[row][col] = matriz[row][col].copy(color = Jewel.getRandomColor())
                }
            }
        }
    }

    private fun cascade() {
        for (row in rows - 1 downTo 0) {
            for (col in 0 until cols) {
                if (matriz[row][col].color == 0) {
                    for (i in row - 1 downTo 0) {
                        if (matriz[i][col].color != 0) {
                            matriz[row][col] = matriz[i][col].copy(row = row)
                            matriz[i][col] = matriz[i][col].copy(color = 0)
                            break
                        }
                    }
                }
            }
        }
    }

    private fun removeBoardLines() {
        for (row in rows - 1 downTo 0) {
            for (col in 0 until cols) {
                if (hasPossibleLine(row, col, matriz[row][col].color)) {
                    removeLine(row, col, matriz[row][col].color)
                }
            }
        }
    }

    private fun removeLine(row: Int, col: Int, color: Int) {
        if (hasHorizontalLine(row, col, color)) {
            removeHorizontalLine(row, col, color)
        }
        if (hasVerticalLine(row, col, color)) {
            removeVerticalLine(row, col, color)
        }
    }

    private fun removeVerticalLine(row: Int, col: Int, color: Int) {
        if (row > 1 && color == matriz[row - 1][col].color && color == matriz[row - 2][col].color) {
            matriz[row - 1][col].color = 0
            matriz[row - 2][col].color = 0
        }
        if (row < rows - 2 && color == matriz[row + 1][col].color && color == matriz[row + 2][col].color) {
            matriz[row + 1][col].color = 0
            matriz[row + 2][col].color = 0
        }
        if (row > 0 && row < rows - 1 && color == matriz[row - 1][col].color && color == matriz[row + 1][col].color) {
            matriz[row - 1][col].color = 0
            matriz[row + 1][col].color = 0
        }
        matriz[row][col].color = 0

    }

    private fun removeHorizontalLine(row: Int, col: Int, color: Int) {
        if (col > 1 && color == matriz[row][col - 1].color && color == matriz[row][col - 2].color) {
            matriz[row][col - 1].color = 0
            matriz[row][col - 2].color = 0
        }
        if (col < cols - 2 && color == matriz[row][col + 1].color && color == matriz[row][col + 2].color) {
            matriz[row][col + 1].color = 0
            matriz[row][col + 2].color = 0
        }
        if (col > 0 && col < cols - 1 && color == matriz[row][col - 1].color && color == matriz[row][col + 1].color) {
            matriz[row][col - 1].color = 0
            matriz[row][col + 1].color = 0
        }
        matriz[row][col].color = 0

    }

    private fun verifyBoardHasLines(): Boolean {
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (hasPossibleLine(i, j, matriz[i][j].color)) {
                    return true
                }
            }
        }
        return false
    }

    private fun undoMove(row: Int, col: Int) {
        val jewel = matriz[selectedRow][selectedCol]
        matriz[selectedRow][selectedCol] =
            matriz[row][col].copy(row = selectedRow, col = selectedCol)
        matriz[row][col] = jewel.copy(row = row, col = col)
    }

    private fun hasPossibleLine(row: Int, col: Int, color: Int): Boolean {
        if (color == 0) return false
        return hasHorizontalLine(row, col, color) || hasVerticalLine(row, col, color)
    }

    private fun hasVerticalLine(row: Int, col: Int, color: Int): Boolean {
        // Check 3 in a row
        return (
                row > 1 &&
                        color == matriz[row - 1][col].color
                        && color == matriz[row - 2][col].color)
                ||
                (row < rows - 2
                        && color == matriz[row + 1][col].color
                        && color == matriz[row + 2][col].color)
                || (
                row > 0 && row < rows - 1
                        && color == matriz[row - 1][col].color
                        && color == matriz[row + 1][col].color
                )
    }

    private fun hasHorizontalLine(row: Int, col: Int, color: Int): Boolean {
        return (
                col > 1
                        && color == matriz[row][col - 1].color
                        && color == matriz[row][col - 2].color)
                ||
                (col < cols - 2
                        && color == matriz[row][col + 1].color
                        && color == matriz[row][col + 2].color)
                || (
                col > 0 && col < cols - 1
                        && color == matriz[row][col - 1].color
                        && color == matriz[row][col + 1].color
                )
    }


    private fun clearSelection() {
        matriz[selectedRow][selectedCol].selected = false
        selectedRow = -1
        selectedCol = -1
    }
}