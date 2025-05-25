package com.example.pedeprontoapp

import android.content.Intent
import android.os.Bundle
import android.os.Build
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class CarrinhoActivity : AppCompatActivity() {

    private lateinit var txtTotal: TextView
    private lateinit var listaProdutos: MutableList<Prato>
    private lateinit var carrinhoAdapter: CarrinhoAdapter
    private lateinit var btnPagamento: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrinho)

        listaProdutos = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableArrayListExtra("produtos", Prato::class.java) ?: arrayListOf()
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableArrayListExtra("produtos") ?: arrayListOf()
        }

        txtTotal = findViewById(R.id.txtTotal)
        btnPagamento = findViewById(R.id.btnPagamento)

        val recyclerCarrinho: RecyclerView = findViewById(R.id.recyclerCarrinho)
        recyclerCarrinho.layoutManager = LinearLayoutManager(this)

        carrinhoAdapter = CarrinhoAdapter(listaProdutos) { atualizarTotal() }
        recyclerCarrinho.adapter = carrinhoAdapter

        atualizarTotal()

        btnPagamento.setOnClickListener {
            enviarPedido()
        }
    }

    private fun atualizarTotal() {
        val total = listaProdutos.sumOf { it.preco * it.quantidade }
        txtTotal.text = getString(R.string.total_text, total)
        Log.d("CarrinhoActivity", "Total atualizado: R$ %.2f".format(total))
    }

    private fun enviarPedido() {
        val url = "https://webapp.up.railway.app/api/criarPedido"
        Log.d("CarrinhoActivity", "Enviando pedido para $url")

        val cliente = "Cliente Android"
        val jsonBody = JSONObject().apply {
            put("nome_cliente", cliente)

            val itensArray = JSONArray()
            listaProdutos.forEach { produto ->
                if (produto.quantidade > 0) {
                    val item = JSONObject().apply {
                        put("nome_produto", produto.nome)
                        put("quantidade", produto.quantidade)
                        put("valor", produto.preco)
                    }
                    itensArray.put(item)
                }
            }
            put("itens", itensArray)
        }

        Log.d("CarrinhoActivity", "JSON enviado: $jsonBody")

        val client = OkHttpClient()
        val requestBody = jsonBody.toString().toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("CarrinhoActivity", "Erro ao conectar com o servidor", e)
                runOnUiThread {
                    Toast.makeText(this@CarrinhoActivity, getString(R.string.server_error), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (it.isSuccessful) {
                        val resposta = it.body?.string() ?: "{}"
                        Log.d("CarrinhoActivity", "Resposta do servidor: $resposta")

                        val json = JSONObject(resposta)

                        val token = json.optString("codigo_entrega", "N/A")
                        val status = json.optString("status", "em espera")

                        runOnUiThread {
                            Log.d("CarrinhoActivity", "Pedido confirmado: token = $token, status = $status")
                            val intent = Intent(this@CarrinhoActivity, ConfirmacaoActivity::class.java).apply {
                                putExtra("token", token)
                                putExtra("status", status)
                            }
                            startActivity(intent)
                        }
                    } else {
                        Log.e("CarrinhoActivity", "Erro na resposta do servidor: ${response.code}")
                        runOnUiThread {
                            Toast.makeText(this@CarrinhoActivity, getString(R.string.server_response_error, response.code), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })
    }
}
