package com.garcia.paulo.usolayoutsv4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PedidoActivity : AppCompatActivity() {
    private lateinit var textViewInfo: TextView
    private lateinit var btnLlamar: Button
    private lateinit var btnWsp: Button
    private lateinit var btnMaps: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)

        // Inicialización de vistas
        textViewInfo = findViewById(R.id.textView_info)
        btnLlamar = findViewById(R.id.btn_llamar)
        btnWsp = findViewById(R.id.btn_wsp)
        btnMaps = findViewById(R.id.btn_maps)

        // Recibir los datos del intent
        val nombre = intent.getStringExtra("nombre")
        val numero = intent.getStringExtra("numero")
        val productos = intent.getStringExtra("productos")
        val direccion = intent.getStringExtra("direccion")

        // Mostrar la información en el TextView
        textViewInfo.text = "Nombre: $nombre\nNúmero: $numero\nProductos: $productos\nDirección: $direccion"

        // Llamar al cliente
        btnLlamar.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$numero")
            }
            startActivity(intent)
        }

        // Enviar mensaje de WhatsApp
        btnWsp.setOnClickListener {
            val mensaje = "Hola $nombre, tus productos: $productos están en camino a la dirección: $direccion"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://api.whatsapp.com/send?text=${Uri.encode(mensaje)}")
            }
            startActivity(intent)
        }

        // Abrir Google Maps con la dirección del cliente
        btnMaps.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$direccion"))
            startActivity(intent)
        }
    }
}
