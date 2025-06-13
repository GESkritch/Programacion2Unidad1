package com.example.pedidosrestauranteunidad1.model

data class Platillo(
    val nombre: String,
    val precioUnitario: Int,
    var cantidad: Int = 0
) {
    fun calcularSubtotal(): Int {
        return precioUnitario * cantidad
    }
}