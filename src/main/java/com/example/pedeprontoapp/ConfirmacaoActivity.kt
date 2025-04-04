package com.example.pedeprontoapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConfirmacaoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmacao)

        val txtStatus: TextView = findViewById(R.id.txtStatus)
        val txtToken: TextView = findViewById(R.id.txtToken)

        // Recebe os dados do pedido enviados pela tela anterior
        val status = intent.getStringExtra("status") ?: "Desconhecido"
        val token = intent.getStringExtra("token") ?: "----"

        txtStatus.text = "Status do Pedido: $status"
        txtToken.text = "Código de Entrega: $token"
    }
}
