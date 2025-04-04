package com.example.pedeprontoapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CarrinhoActivity : AppCompatActivity() {

    private lateinit var txtTotal: TextView
    private lateinit var listaProdutos: MutableList<Prato>
    private lateinit var carrinhoAdapter: CarrinhoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrinho)

        listaProdutos = intent.getParcelableArrayListExtra("produtos") ?: arrayListOf()
        txtTotal = findViewById(R.id.txtTotal)

        val recyclerCarrinho: RecyclerView = findViewById(R.id.recyclerCarrinho)
        recyclerCarrinho.layoutManager = LinearLayoutManager(this)

        carrinhoAdapter = CarrinhoAdapter(listaProdutos) { atualizarTotal() }
        recyclerCarrinho.adapter = carrinhoAdapter

        atualizarTotal()
    }

    private fun atualizarTotal() {
        val total = listaProdutos.sumOf { it.preco * it.quantidade }
        txtTotal.text = "Total: R$ %.2f".format(total)
    }
}
