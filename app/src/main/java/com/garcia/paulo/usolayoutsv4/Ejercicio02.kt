package com.garcia.paulo.usolayoutsv4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Ejercicio02 : AppCompatActivity() {
    private lateinit var inputNombre: EditText
    private lateinit var inputNumero: EditText
    private lateinit var inputProductos: EditText
    private lateinit var inputCiudad: EditText
    private lateinit var inputDireccion: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var btnLlamar: Button
    private lateinit var btnWsp: Button
    private lateinit var btnMaps: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejercicio02)

        // Inicialización de vistas
        inputNombre = findViewById(R.id.input_nombre)
        inputNumero = findViewById(R.id.input_numero)
        inputProductos = findViewById(R.id.input_productos)
        inputCiudad = findViewById(R.id.input_ciudad)
        inputDireccion = findViewById(R.id.input_direccion)
        btnRegistrar = findViewById(R.id.btn_registrar)
        btnLlamar = findViewById(R.id.btn_llamar)
        btnWsp = findViewById(R.id.btn_wsp)
        btnMaps = findViewById(R.id.btn_maps)

        btnRegistrar.setOnClickListener {
            val nombre = inputNombre.text.toString()
            val numero = inputNumero.text.toString()
            val productos = inputProductos.text.toString()
            val ciudad = inputCiudad.text.toString()
            val direccion = inputDireccion.text.toString()

            if (nombre.isEmpty() || numero.isEmpty() || productos.isEmpty() || ciudad.isEmpty() || direccion.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, PedidoActivity::class.java).apply {
                    putExtra("nombre", nombre)
                    putExtra("numero", numero)
                    putExtra("productos", productos)
                    putExtra("ciudad", ciudad)
                    putExtra("direccion", direccion)
                }
                startActivity(intent)
            }
        }

        btnLlamar.setOnClickListener {
            val numeroTelefono = inputNumero.text.toString()
            if (numeroTelefono.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$numeroTelefono"))
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, ingresa un número de teléfono", Toast.LENGTH_SHORT).show()
            }
        }

        btnWsp.setOnClickListener {
            val nombre = inputNombre.text.toString()
            val productos = inputProductos.text.toString()
            val direccion = inputDireccion.text.toString()

            if (nombre.isNotEmpty() && productos.isNotEmpty() && direccion.isNotEmpty()) {
                val mensaje = "Hola $nombre, tus productos: $productos están en camino a la dirección: $direccion"
                val url = "https://api.whatsapp.com/send?text=${Uri.encode(mensaje)}"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos para enviar el mensaje", Toast.LENGTH_SHORT).show()
            }
        }

        btnMaps.setOnClickListener {
            val direccion = inputDireccion.text.toString()
            if (direccion.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$direccion"))
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, ingresa una dirección", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
