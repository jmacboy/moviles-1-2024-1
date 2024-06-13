package com.example.practico4solucion.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practico4solucion.databinding.GeneroItemLayoutBinding
import com.example.practico4solucion.databinding.LibroItemLayoutBinding
import com.example.practico4solucion.models.Genero
import com.example.practico4solucion.models.Generos
import com.example.practico4solucion.models.Libros

class GenreAdapter(
    private val genreList: Generos,
    private val listener: OnGeneroClickListener
) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding =
            GeneroItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return GenreViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genero = genreList[position]
        holder.bind(genero, listener)
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    fun updateData(generos: Generos?) {
        generos?.let {
            genreList.clear()
            genreList.addAll(generos)
            notifyDataSetChanged()
        }
    }

    class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(genero: Genero, listener: OnGeneroClickListener) {
            val binding = GeneroItemLayoutBinding.bind(itemView)
            binding.apply {
                lblGenreName.text = genero.nombre
                root.setOnClickListener { listener.onGeneroClick(genero) }
            }
        }

    }

    interface OnGeneroClickListener {
        fun onGeneroClick(genero: Genero)
        fun onGeneroDelete(genero: Genero)
    }
}