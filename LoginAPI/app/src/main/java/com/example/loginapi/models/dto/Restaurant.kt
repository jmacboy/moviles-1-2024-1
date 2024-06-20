package com.example.loginapi.models.dto

class Restaurant(
    val name: String,
    val address: String = "Av 123",
    val city: String,
    val description: String = "venta de comida",
) {
}