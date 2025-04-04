package com.example.pedeprontoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarrinhoAdapter(
    private val listaProdutos: MutableList<Prato>,
    private val onUpdateTotal: () -> Unit
) : RecyclerView.Adapter<CarrinhoAdapter.CarrinhoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrinhoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carrinho, parent, false)
        return CarrinhoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarrinhoViewHolder, position: Int) {
        val produto = listaProdutos[position]

        holder.imgProduto.setImageResource(produto.imagem)
        holder.txtNome.text = produto.nome
        holder.txtPreco.text = "R$ %.2f".format(produto.preco * produto.quantidade)
        holder.txtQuantidade.text = produto.quantidade.toString()

        holder.btnMais.setOnClickListener {
            produto.quantidade++
            notifyItemChanged(position)
            onUpdateTotal()
        }

        holder.btnMenos.setOnClickListener {
            if (produto.quantidade > 1) {
                produto.quantidade--
                notifyItemChanged(position)
            } else {
                listaProdutos.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, listaProdutos.size)
            }
            onUpdateTotal()
        }
    }

    override fun getItemCount(): Int = listaProdutos.size

    class CarrinhoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgProduto: ImageView = view.findViewById(R.id.imgProduto)
        val txtNome: TextView = view.findViewById(R.id.txtNome)
        val txtPreco: TextView = view.findViewById(R.id.txtPreco)
        val txtQuantidade: TextView = view.findViewById(R.id.txtQuantidade)
        val btnMais: Button = view.findViewById(R.id.btnMais)
        val btnMenos: Button = view.findViewById(R.id.btnMenos)
    }
}
