package com.example.pedeprontoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class PratoAdapter(
    private val listaPratos: List<Prato>,
    private val onItemToggled: (Prato) -> Unit
) : RecyclerView.Adapter<PratoAdapter.PratoViewHolder>() {

    private val pratosSelecionados = mutableSetOf<Prato>()

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

        // Alterar o fundo quando selecionado
        holder.itemView.setBackgroundColor(
            if (pratosSelecionados.contains(prato))
                ContextCompat.getColor(holder.itemView.context, R.color.light_gray)
            else
                ContextCompat.getColor(holder.itemView.context, android.R.color.white)
        )

        // Clique para selecionar/deselecionar
        holder.itemView.setOnClickListener {
            if (pratosSelecionados.contains(prato)) {
                pratosSelecionados.remove(prato)
            } else {
                pratosSelecionados.add(prato)
            }
            notifyItemChanged(position)
            onItemToggled(prato)
        }
    }

    override fun getItemCount(): Int = listaPratos.size

    class PratoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPrato: ImageView = view.findViewById(R.id.imgPrato)
        val txtNome: TextView = view.findViewById(R.id.txtNome)
        val txtDescricao: TextView = view.findViewById(R.id.txtDescricao)
        val txtPreco: TextView = view.findViewById(R.id.txtPreco)
    }
}
