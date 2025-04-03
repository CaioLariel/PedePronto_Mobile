package com.example.pedeprontoapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CarrinhoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrinho)

        val listaProdutos = intent.getParcelableArrayListExtra<Prato>("produtos") ?: arrayListOf()

        val recyclerCarrinho: RecyclerView = findViewById(R.id.recyclerCarrinho)
        recyclerCarrinho.layoutManager = LinearLayoutManager(this)
        recyclerCarrinho.adapter = CarrinhoAdapter(listaProdutos)

        val txtTotal: TextView = findViewById(R.id.txtTotal)
        val total = listaProdutos.sumOf { it.preco }
        txtTotal.text = "Total: R$ %.2f".format(total)
    }
}
