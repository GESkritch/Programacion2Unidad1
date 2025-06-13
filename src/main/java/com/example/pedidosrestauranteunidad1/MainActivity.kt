package com.example.pedidosrestauranteunidad1.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import com.example.pedidosrestauranteunidad1.R
import com.example.pedidosrestauranteunidad1.model.Platillo
import com.example.pedidosrestauranteunidad1.model.Pedido
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var etCantidad1: EditText
    private lateinit var etCantidad2: EditText
    private lateinit var switchPropina: Switch
    private lateinit var tvSubtotal1: TextView
    private lateinit var tvSubtotal2: TextView
    private lateinit var tvTotalSinPropina: TextView
    private lateinit var tvPropina: TextView
    private lateinit var tvTotalConPropina: TextView

    private lateinit var pastelDeChoclo: Platillo
    private lateinit var cazuela: Platillo
    private lateinit var pedido: Pedido

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas con findViewById, verificando que los IDs coincidan
        etCantidad1 = findViewById(R.id.etCantidad1)
        etCantidad2 = findViewById(R.id.etCantidad2)
        switchPropina = findViewById(R.id.switchPropina)
        tvSubtotal1 = findViewById(R.id.tvSubtotal1)
        tvSubtotal2 = findViewById(R.id.tvSubtotal2)
        tvTotalSinPropina = findViewById(R.id.tvTotalSinPropina)
        tvPropina = findViewById(R.id.tvPropina)
        tvTotalConPropina = findViewById(R.id.tvTotalConPropina)

        // Inicializar objetos del modelo
        pastelDeChoclo = Platillo("Pastel de Choclo", 12000)
        cazuela = Platillo("Cazuela", 10000)
        pedido = Pedido(listOf(pastelDeChoclo, cazuela))

        // Configurar eventos para actualizar valores
        configurarEventos()
    }

    private fun configurarEventos() {
        // Detectar cambios en EditText de Pastel de Choclo
        etCantidad1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                pastelDeChoclo.cantidad = s.toString().toIntOrNull() ?: 0
                actualizarTotales()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Detectar cambios en EditText de Cazuela
        etCantidad2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                cazuela.cantidad = s.toString().toIntOrNull() ?: 0
                actualizarTotales()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Detectar cambio en el Switch para la propina
        switchPropina.setOnCheckedChangeListener { _, isChecked ->
            pedido.actualizarPropina(isChecked)
            actualizarTotales()
        }
    }

    private fun actualizarTotales() {
        tvSubtotal1.text = "Subtotal: ${formatearMoneda(pastelDeChoclo.calcularSubtotal())}"
        tvSubtotal2.text = "Subtotal: ${formatearMoneda(cazuela.calcularSubtotal())}"
        tvTotalSinPropina.text = "Total sin propina: ${formatearMoneda(pedido.calcularTotalSinPropina())}"
        tvPropina.text = "Propina: ${formatearMoneda(pedido.calcularPropina())}"
        tvTotalConPropina.text = "Total con propina: ${formatearMoneda(pedido.calcularTotalConPropina())}"
    }

    private fun formatearMoneda(valor: Int): String {
        val formato = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
        return formato.format(valor)
    }
}