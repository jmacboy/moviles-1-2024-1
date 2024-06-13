package com.example.practico4solucion.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practico4solucion.databinding.LibroItemLayoutBinding
import com.example.practico4solucion.models.Libro
import com.example.practico4solucion.models.Libros

class BookAdapter(
    private val bookList: Libros,
    private val listener: OnLibroClickListener
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding =
            LibroItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return BookViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val libro = bookList[position]
        holder.bind(libro, listener)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun updateData(libros: Libros?) {
        libros?.let {
            bookList.clear()
            bookList.addAll(libros)
            notifyDataSetChanged()
        }
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(libro: Libro, listener: OnLibroClickListener) {
            val binding = LibroItemLayoutBinding.bind(itemView)
            binding.apply {
                lblBookName.text = libro.nombre
                lblBookGrade.text = libro.calificacion.toString()
                lblBookAuthor.text = libro.autor
                Glide.with(itemView.context).load(libro.imagen).into(imgBookPicture)
                root.setOnClickListener { listener.onLibroClick(libro) }
            }
        }

    }

    interface OnLibroClickListener {
        fun onLibroClick(libro: Libro)
        fun onLibroDelete(libro: Libro)
    }
}