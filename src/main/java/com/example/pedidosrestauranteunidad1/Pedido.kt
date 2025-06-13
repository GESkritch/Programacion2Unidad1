package com.example.pedidosrestauranteunidad1.model

class Pedido(private val platillos: List<Platillo>, private var incluirPropina: Boolean = false) {

    fun calcularTotalSinPropina(): Int {
        return platillos.sumOf { it.calcularSubtotal() }
    }

    fun calcularPropina(): Int {
        return if (incluirPropina) (calcularTotalSinPropina() * 0.1).toInt() else 0
    }

    fun calcularTotalConPropina(): Int {
        return calcularTotalSinPropina() + calcularPropina()
    }

    fun actualizarPropina(incluir: Boolean) {
        incluirPropina = incluir
    }
}