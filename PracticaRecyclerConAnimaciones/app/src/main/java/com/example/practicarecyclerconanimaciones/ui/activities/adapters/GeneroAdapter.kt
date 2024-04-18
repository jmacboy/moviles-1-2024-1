package com.example.practicarecyclerconanimaciones.ui.activities.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicarecyclerconanimaciones.databinding.GeneroListItemBinding
import com.example.practicarecyclerconanimaciones.models.Genero

class GeneroAdapter(
    private val generos: ArrayList<Genero>,
    private val listener: OnGeneroClickListener
) : RecyclerView.Adapter<GeneroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneroViewHolder {
        val binding =
            GeneroListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return GeneroViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: GeneroViewHolder, position: Int) {
        val genero = generos[position]
        holder.bind(genero, listener)
    }

    override fun getItemCount(): Int {
        return generos.size
    }

    fun addGenero(genero: Genero) {
        generos.add(1, genero)
        notifyItemInserted(1)
    }

    fun updateGenero(genero: Genero) {
        val index = generos.indexOfFirst { it.id == genero.id }
        generos[index] = genero
        notifyItemChanged(index)
    }

    fun removeGenero(genero: Genero) {
        val index = generos.indexOfFirst { it.id == genero.id }
        generos.removeAt(index)
        notifyItemRemoved(index)
    }
}

class GeneroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(genero: Genero, listener: OnGeneroClickListener) {
        GeneroListItemBinding.bind(itemView).apply {
            lblGeneroName.setText(genero.nombre)
            Glide.with(itemView.context)
                .load(genero.imagenUrl)
                .into(imgGenero);

            root.setOnClickListener {
                listener.onGeneroClick(genero)
            }
            view.setOnClickListener{
                listener.onGeneroClick(genero)
            }
            view.setOnLongClickListener{
                listener.onDeleteClick(genero)
                true
            }
        }


    }
}

interface OnGeneroClickListener {
    fun onGeneroClick(genero: Genero)
    fun onDeleteClick(genero: Genero)
}