package com.example.pedeprontoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarrinhoAdapter(private val listaProdutos: List<Prato>) :
    RecyclerView.Adapter<CarrinhoAdapter.CarrinhoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrinhoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carrinho, parent, false)
        return CarrinhoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarrinhoViewHolder, position: Int) {
        val produto = listaProdutos[position]
        holder.imgProduto.setImageResource(produto.imagem)
        holder.txtNome.text = produto.nome
        holder.txtPreco.text = "R$ %.2f".format(produto.preco)
    }

    override fun getItemCount(): Int = listaProdutos.size

    class CarrinhoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgProduto: ImageView = view.findViewById(R.id.imgProduto)
        val txtNome: TextView = view.findViewById(R.id.txtNome)
        val txtPreco: TextView = view.findViewById(R.id.txtPreco)
    }
}
