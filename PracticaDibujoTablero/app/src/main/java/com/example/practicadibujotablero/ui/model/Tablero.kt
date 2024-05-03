package com.example.practicadibujotablero.ui.model

class Tablero {
    var filas = 8
    var columnas = 8

    // matriz llena de números del 1 al 6
    var matriz: Array<Array<Int>> = arrayOf(
        arrayOf(7, 2, 3, 4, 5, 6, 1, 2),
        arrayOf(2, 3, 4, 5, 6, 1, 2, 3),
        arrayOf(3, 4, 5, 6, 1, 2, 3, 4),
        arrayOf(4, 5, 2, 1, 2, 3, 4, 5),
        arrayOf(5, 6, 1, 2, 3, 4, 5, 6),
        arrayOf(6, 3, 2, 3, 4, 5, 6, 1),
        arrayOf(1, 2, 3, 4, 5, 6, 1, 2),
        arrayOf(2, 3, 4, 5, 7, 1, 2, 3)
    )
}