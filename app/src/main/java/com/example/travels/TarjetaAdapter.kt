package com.example.travels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class TarjetaAdapter(var destinosList: List<Tarjeta>, val onClick: (View) -> Unit) : RecyclerView.Adapter<TarjetaAdapter.TarjViewHolder>() {

    class TarjViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imagen = view.findViewById<ImageView>(R.id.imagenDestino)

        fun bind(destinosModel: Tarjeta, onClick: (View) -> Unit) = with(itemView){
            imagen.setImageResource(destinosModel.imagenDestino)
            setOnClickListener{ onClick(itemView)}
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TarjViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_destinos, viewGroup, false)
        return TarjViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: TarjViewHolder, pos: Int) {
        val item = destinosList.get(pos)
        viewHolder.bind(item, onClick)
    }

    override fun getItemCount(): Int {
        return destinosList.size
    }
}