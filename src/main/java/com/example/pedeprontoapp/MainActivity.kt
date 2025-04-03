package com.example.pedeprontoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val produtosSelecionados = mutableListOf<Prato>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerPratos: RecyclerView = findViewById(R.id.recyclerPratos)
        val recyclerBebidas: RecyclerView = findViewById(R.id.recyclerBebidas)
        val recyclerSalgados: RecyclerView = findViewById(R.id.recyclerSalgados)
        val recyclerDoces: RecyclerView = findViewById(R.id.recyclerDoces)

        recyclerPratos.layoutManager = GridLayoutManager(this, 2)
        recyclerBebidas.layoutManager = GridLayoutManager(this, 2)
        recyclerSalgados.layoutManager = GridLayoutManager(this, 2)
        recyclerDoces.layoutManager = GridLayoutManager(this, 2)

        val listaPratos = listOf(
            Prato("Hambúrguer Clássico", "Pão, carne suculenta, queijo e molho especial.", 19.99, R.drawable.burger),
            Prato("Pizza Margherita", "Massa crocante, molho de tomate, mussarela e manjericão.", 29.99, R.drawable.pizza)
        )

        val listaBebidas = listOf(
            Prato("Refrigerante", "Lata de 350ml de refrigerante gelado.", 5.99, R.drawable.soda),
            Prato("Suco Natural", "Suco natural de laranja feito na hora.", 7.99, R.drawable.juice)
        )

        val listaSalgados = listOf(
            Prato("Coxinha", "Massa crocante recheada com frango cremoso.", 6.50, R.drawable.coxinha),
            Prato("Esfiha", "Esfiha de carne bem temperada.", 5.00, R.drawable.esfiha)
        )

        val listaDoces = listOf(
            Prato("Brigadeiro", "Docinho de chocolate com granulado.", 3.50, R.drawable.brigadeiro),
            Prato("Bolo de Chocolate", "Fatia de bolo com recheio cremoso de chocolate.", 8.00, R.drawable.bolo_chocolate)
        )

        recyclerPratos.adapter = PratoAdapter(listaPratos) { prato -> produtosSelecionados.add(prato) }
        recyclerBebidas.adapter = PratoAdapter(listaBebidas) { prato -> produtosSelecionados.add(prato) }
        recyclerSalgados.adapter = PratoAdapter(listaSalgados) { prato -> produtosSelecionados.add(prato) }
        recyclerDoces.adapter = PratoAdapter(listaDoces) { prato -> produtosSelecionados.add(prato) }

        // Configura o botão para ir ao carrinho
        val btnCarrinho: Button = findViewById(R.id.btnCarrinho)
        btnCarrinho.setOnClickListener {
            if (produtosSelecionados.isNotEmpty()) {
                val intent = Intent(this, CarrinhoActivity::class.java)
                intent.putParcelableArrayListExtra("produtos", ArrayList(produtosSelecionados))
                startActivity(intent)
            }
        }
    }
}
