package com.example.practicaapipersonas.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaapipersonas.databinding.ProductItemLayoutBinding
import com.example.practicaapipersonas.models.Producto
import com.example.practicaapipersonas.models.Productos

class ProductoAdapter(val personaList: Productos, val listener: OnProductoClickListener) :
    RecyclerView.Adapter<ProductoAdapter.PersonaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val binding =
            ProductItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return PersonaViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return personaList.size
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val persona = personaList[position]
        holder.bind(persona, listener)
    }

    fun updateData(personaList: Productos) {
        this.personaList.clear()
        this.personaList.addAll(personaList)
        notifyDataSetChanged()
    }

    class PersonaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(producto: Producto, listener: OnProductoClickListener) {
            val binding = ProductItemLayoutBinding.bind(itemView)
            binding.apply {
                lblProductName.text = producto.nombre
                lblProductPrice.text = producto.precioActual
//                btnDeleteCategory.setOnClickListener {
//                    listener.onProductoDelete(producto)
//                }
//                lblCategoryName.setOnClickListener {
//                    listener.onProductoClick(producto)
//                }
//                root.setOnClickListener { listener.onProductoClick(producto) }
            }

        }
    }

    interface OnProductoClickListener {
//        fun onProductoClick(producto: Producto)
//        fun onProductoDelete(producto: Producto)
    }
}