package com.example.pedeprontoapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PratoAdapter(private val listaPratos: List<Prato>, private val onItemAdded: (Prato) -> Unit) :
    RecyclerView.Adapter<PratoAdapter.PratoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PratoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prato, parent, false)
        return PratoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PratoViewHolder, position: Int) {
        val prato = listaPratos[position]
        holder.imgPrato.setImageResource(prato.imagem)
        holder.txtNome.text = prato.nome
        holder.txtDescricao.text = prato.descricao
        holder.txtPreco.text = "R$ %.2f".format(prato.preco)

        // Botão de adicionar produto ao carrinho
        holder.btnAdicionar.setOnClickListener {
            onItemAdded(prato)
        }
    }

    override fun getItemCount(): Int = listaPratos.size

    class PratoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPrato: ImageView = view.findViewById(R.id.imgPrato)
        val txtNome: TextView = view.findViewById(R.id.txtNome)
        val txtDescricao: TextView = view.findViewById(R.id.txtDescricao)
        val txtPreco: TextView = view.findViewById(R.id.txtPreco)
        val btnAdicionar: Button = view.findViewById(R.id.btnAdicionar)
    }
}
